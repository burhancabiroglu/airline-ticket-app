package com.cabir.airlineticketapp.ui.flights

import com.cabir.airlineticketapp.core.base.VMState
import java.util.Date

interface FlightsListVMState: VMState {
    class OnDataReady: FlightsListVMState
    class OnDateChange(val date: Date): FlightsListVMState
}