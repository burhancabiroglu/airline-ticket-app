package com.cabir.airlineticketapp.ui.flights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cabir.airlineticketapp.core.base.BaseViewModel
import com.cabir.airlineticketapp.core.base.ISuccess
import com.cabir.airlineticketapp.data.model.price.PriceHistory
import com.cabir.airlineticketapp.data.repo.ApiRepo
import com.cabir.airlineticketapp.util.extension.parseUnicode
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

    private var _priceHistory = MutableLiveData<PriceHistory>()
    var priceHistory: PriceHistory?
        get() = _priceHistory.value
        set(value) { value?.let {  _priceHistory.value = it} }

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
                        state.value = FlightsListVMState.OnDataReady()
                    }
                }
                else -> Unit
            }
        }
    }
}