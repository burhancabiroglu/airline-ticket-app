package com.cabir.airlineticketapp.ui.flights

import com.cabir.airlineticketapp.core.base.VMState

interface FlightsListVMState: VMState {
    class OnDataReady: FlightsListVMState
}