package com.cabir.airlineticketapp.ui.flights

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.components.tabitem.FlightTabData
import com.cabir.airlineticketapp.components.tabitem.FlightTabItem
import com.cabir.airlineticketapp.core.base.BaseFragment
import com.cabir.airlineticketapp.core.base.VMState
import com.cabir.airlineticketapp.databinding.FragmentFlightsListBinding
import com.cabir.airlineticketapp.util.extension.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightsListFragment : BaseFragment<FlightsListViewModel, FragmentFlightsListBinding>() {
    override val viewModel: FlightsListViewModel by viewModels()
    override val layoutRes: Int = R.layout.fragment_flights_list

    override fun initUI() {
        viewModel.getData()
    }

    override fun onChangedScreenState(state: VMState) {
        when (state) {
            is FlightsListVMState.OnDataReady -> {
                val parent = view as ViewGroup
                val tabLayout = binding.flightsTabLayout
                val prices = viewModel.priceHistory.value!!.departure
                val formattedDate = viewModel.departureDate.value!!.formatDate()
                val departureDate = viewModel.departureDate.value!!.toDate(DateStrategy.FORMAT2)!!
                viewModel.tabs.add(
                    FlightTabItem.create(
                        requireContext(),
                        parent,
                        tabLayout,
                        FlightTabData(
                            getString(R.string.prev_day),
                            prices.previousDayPrice.toNumberFormat(),
                            departureDate.sumDay(-1)
                        )
                    ) {
                        viewModel.state.value = FlightsListVMState.OnDateChange(it)
                    }
                )
                viewModel.tabs.add(
                    FlightTabItem.create(
                        requireContext(),
                        parent,
                        tabLayout,
                        FlightTabData(
                            formattedDate, prices.previousDayPrice.toNumberFormat(),
                            departureDate,
                            true
                        ),
                    ) {
                        viewModel.state.value = FlightsListVMState.OnDateChange(it)
                    }
                )
                viewModel.tabs.add(
                    FlightTabItem.create(
                        requireContext(),
                        parent,
                        tabLayout,
                        FlightTabData(
                            getString(R.string.next_day),
                            prices.nextDayPrice.toNumberFormat(),
                            departureDate.sumDay(1)
                        )
                    ) {
                        viewModel.state.value = FlightsListVMState.OnDateChange(it)
                    }
                )
            }
            is FlightsListVMState.OnDateChange -> viewModel.updateFilters(state.date)
        }
    }
}