package com.cabir.airlineticketapp.ui.flights

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cabir.airlineticketapp.core.base.BaseViewModel
import com.cabir.airlineticketapp.core.base.ILoading
import com.cabir.airlineticketapp.core.base.ISuccess
import com.cabir.airlineticketapp.data.model.flightsearch.SearchResponse
import com.cabir.airlineticketapp.data.repo.ApiRepo
import com.cabir.airlineticketapp.ui.adapter.FlightsFragmentAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightsListViewModel @Inject constructor(
    private val appRepo: ApiRepo
) : BaseViewModel() {

    fun getData() {
        Log.e("TAG", "getData: working ", )
        viewModelScope.launch {
            when(val result = appRepo.search()) {
                is ISuccess -> Log.e("TAG",result.data.toString())
                is ILoading -> Log.e("TAG","loading")
                else -> Log.e("hiçbir şey çıkmadı", result.message.toString())
            }
        }
    }
}