package com.cabir.airlineticketapp.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cabir.airlineticketapp.components.tabitem.FlightTabItem
import com.cabir.airlineticketapp.core.base.BaseViewModel
import com.cabir.airlineticketapp.core.base.ISuccess
import com.cabir.airlineticketapp.data.model.flightsearch.FlightSearch
import com.cabir.airlineticketapp.data.model.price.PriceHistory
import com.cabir.airlineticketapp.data.repo.ApiRepo
import com.cabir.airlineticketapp.ui.adapter.FlightItem
import com.cabir.airlineticketapp.ui.adapter.FlightsRecyclerViewAdapter
import com.cabir.airlineticketapp.util.extension.DateStrategy
import com.cabir.airlineticketapp.util.extension.parseUnicode
import com.cabir.airlineticketapp.util.extension.toCurrencyFormat
import com.cabir.airlineticketapp.util.extension.toDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FlightsListViewModel @Inject constructor(
    private val appRepo: ApiRepo
) : BaseViewModel() {

    val cachedResponse = MutableLiveData<FlightSearch>()
    val origin = MutableLiveData<String>()
    val destination = MutableLiveData<String>()
    val isOneWay = MutableLiveData<Boolean>()
    val passengerCount = MutableLiveData<Int>()
    val departureDate = MutableLiveData<String>()
    val priceHistory = MutableLiveData<PriceHistory>()
    val adapter = FlightsRecyclerViewAdapter(arrayListOf())
    val tabs = ArrayList<FlightTabItem>()

    fun getData() {
        viewModelScope.launch {
            when(val result = appRepo.search()) {
                is ISuccess -> {
                    result.data?.data?.let {
                        processData(it)
                        cachedResponse.value = it
                        state.value = FlightsListVMState.OnDataReady()
                    }
                }
                else -> Unit
            }
        }
    }


    private fun processData(data: FlightSearch) {
        origin.value = data.searchParameters.origin.cityName.parseUnicode()
        destination.value = data.searchParameters.destination.cityName.parseUnicode()
        isOneWay.value = data.searchParameters.isOneWay
        passengerCount.value = data.searchParameters.passengerCount
        priceHistory.value = data.priceHistory
        departureDate.value = data.searchParameters.departureDate
        departureDate.value?.toDate(DateStrategy.FORMAT2)?.let { processList(data, it) }
    }

    private fun processList(data: FlightSearch,filterDate: Date) {
        val list = data.flights.departure.filter {
            it.segments[0].departureDatetime.date.toDate()?.equals(filterDate)?:false
        }.map { flight ->
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
    fun updateFilters(filterDate: Date) {
        cachedResponse.value?.let { processList(it,filterDate) }
    }
}