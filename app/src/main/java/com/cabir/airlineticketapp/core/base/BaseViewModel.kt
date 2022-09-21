package com.cabir.airlineticketapp.core.base

import androidx.lifecycle.ViewModel
import com.cabir.airlineticketapp.util.SingleLiveEvent

abstract class BaseViewModel: ViewModel() {
    val state = SingleLiveEvent<VMState>()
}