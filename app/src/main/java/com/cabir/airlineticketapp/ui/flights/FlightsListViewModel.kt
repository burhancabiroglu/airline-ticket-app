package com.cabir.airlineticketapp.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cabir.airlineticketapp.components.tabitem.FlightTabItem
import com.cabir.airlineticketapp.core.base.BaseViewModel
import com.cabir.airlineticketapp.core.base.ISuccess
import com.cabir.airlineticketapp.data.model.departure.Departure
import com.cabir.airlineticketapp.data.model.flightsearch.FlightSearch
import com.cabir.airlineticketapp.data.model.price.PriceHistory
import com.cabir.airlineticketapp.data.repo.ApiRepo
import com.cabir.airlineticketapp.ui.adapter.FlightItem
import com.cabir.airlineticketapp.ui.adapter.FlightsRecyclerViewAdapter
import com.cabir.airlineticketapp.util.extension.parseUnicode
import com.cabir.airlineticketapp.util.extension.toCurrencyFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightsListViewModel @Inject constructor(
    private val appRepo: ApiRepo
) : BaseViewModel() {

    val origin = MutableLiveData<String>()
    val destination = MutableLiveData<String>()
    val isOneWay = MutableLiveData<Boolean>()
    val passengerCount = MutableLiveData<Int>()
    private val _departureDate = MutableLiveData<String>()
    private var _priceHistory = MutableLiveData<PriceHistory>()
    val adapter = FlightsRecyclerViewAdapter(arrayListOf())

    var priceHistory: PriceHistory?
        get() = _priceHistory.value
        set(value) { value?.let {  _priceHistory.value = it} }

    val departureDate
        get() = _departureDate.value.toString()

    val tabs = ArrayList<FlightTabItem>()

    fun getData() {
        viewModelScope.launch {
            when(val result = appRepo.search()) {
                is ISuccess -> {
                    result.data?.data?.let {
                        processData(it)
                        state.value = FlightsListVMState.OnDataReady()
                    }
                }
                else -> Unit
            }
        }
    }
    fun processData(data: FlightSearch) {
        origin.value = data.searchParameters.origin.cityName.parseUnicode()
        destination.value = data.searchParameters.destination.cityName.parseUnicode()
        isOneWay.value = data.searchParameters.isOneWay
        passengerCount.value = data.searchParameters.passengerCount
        _priceHistory.value = data.priceHistory
        _departureDate.value = data.searchParameters.departureDate
        val list = data.flights.departure.map { flight ->
            val baggage = flight.infos.baggageInfo.firstBaggageCollection!=null
            val allowance = if(baggage) flight.infos.baggageInfo.firstBaggageCollection?.get(0)?.allowance else 0
            FlightItem(
                flight.segments[0].origin,
                flight.segments[0].destination,
                flight.priceBreakdown.total.toCurrencyFormat(),
                flight.segments[0].departureDatetime.time,
                flight.segments[0].arrivalDatetime.time,
                marketingAirline = data.airlines.find { it.code == flight.segments[0].marketingAirline }!!,
                operatingAirline = data.airlines.find { it.code == flight.segments[0].operatingAirline }!!,
                flight.priceBreakdown.displayedCurrency,
                baggage = flight.infos.baggageInfo.firstBaggageCollection!=null,
                baggageAllowance = allowance?:0,
                isDirect = flight.differentAirlineCount==1

            )
        }
        adapter.updateData(list)
    }
}