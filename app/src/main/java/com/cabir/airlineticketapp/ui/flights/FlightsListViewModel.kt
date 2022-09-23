package com.cabir.airlineticketapp.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cabir.airlineticketapp.core.base.BaseViewModel
import com.cabir.airlineticketapp.core.base.ISuccess
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

    fun getData() {
        viewModelScope.launch {
            when(val result = appRepo.search()) {
                is ISuccess -> {
                    result.data?.data?.let {
                        origin.value = it.searchParameters.origin.cityName.parseUnicode()
                        destination.value = it.searchParameters.destination.cityName.parseUnicode()
                        isOneWay.value = it.searchParameters.isOneWay
                        passengerCount.value = it.searchParameters.passengerCount
                        _priceHistory.value = it.priceHistory
                        _departureDate.value = it.searchParameters.departureDate
                        val list = it.flights.departure.map { flight ->
                            FlightItem(
                                flight.segments[0].origin,
                                flight.segments[0].destination,
                                flight.priceBreakdown.total.toCurrencyFormat(),
                                flight.segments[0].departureDatetime.time,
                                flight.segments[0].arrivalDatetime.time,
                                marketingAirline = it.airlines.find { it.code == flight.segments[0].marketingAirline }!!,
                                operatingAirline = it.airlines.find { it.code == flight.segments[0].operatingAirline }!!,
                            )
                        }
                        adapter.updateData(list)
                        state.value = FlightsListVMState.OnDataReady()
                    }
                }
                else -> Unit
            }
        }
    }

    fun extractFlightItem() {

    }
}