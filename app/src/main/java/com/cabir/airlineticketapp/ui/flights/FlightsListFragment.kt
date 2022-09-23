package com.cabir.airlineticketapp.ui.flights

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.core.base.BaseFragment
import com.cabir.airlineticketapp.core.base.VMState
import com.cabir.airlineticketapp.databinding.FragmentFlightsListBinding
import com.cabir.airlineticketapp.components.tabitem.FlightTabItem
import com.cabir.airlineticketapp.util.extension.formatDate
import com.cabir.airlineticketapp.util.extension.toNumberFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightsListFragment: BaseFragment<FlightsListViewModel,FragmentFlightsListBinding>() {
    override val viewModel: FlightsListViewModel by viewModels()
    override val layoutRes: Int  = R.layout.fragment_flights_list

    override fun initUI() {
        viewModel.getData()
    }

    override fun onChangedScreenState(state: VMState) {
        when(state){
            is FlightsListVMState.OnDataReady -> {
                val vg = view as ViewGroup
                val tl = binding.flightsTabLayout
                val ph = viewModel.priceHistory?.departure
                context?.let {
                    FlightTabItem.create(it,vg,tl, Pair(getString(R.string.prev_day),ph?.previousDayPrice!!.toNumberFormat()))
                    FlightTabItem.create(it,vg,tl, Pair(viewModel.departureDate.formatDate(), ph.previousDayPrice.toNumberFormat()), true)
                    FlightTabItem.create(it,vg,tl, Pair(getString(R.string.next_day),ph.nextDayPrice.toNumberFormat()))
                }

            }
        }
    }
}