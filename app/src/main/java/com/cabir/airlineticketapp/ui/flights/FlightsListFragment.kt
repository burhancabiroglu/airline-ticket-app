package com.cabir.airlineticketapp.ui.flights

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.components.tabitem.FlightTabData
import com.cabir.airlineticketapp.components.tabitem.FlightTabItem
import com.cabir.airlineticketapp.core.base.BaseFragment
import com.cabir.airlineticketapp.core.base.VMState
import com.cabir.airlineticketapp.databinding.FragmentFlightsListBinding
import com.cabir.airlineticketapp.util.extension.DateStrategy
import com.cabir.airlineticketapp.util.extension.formatDate
import com.cabir.airlineticketapp.util.extension.toDate
import com.cabir.airlineticketapp.util.extension.toNumberFormat
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

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
                val prices = viewModel.priceHistory!!.departure
                viewModel.tabs.add(
                    FlightTabItem.create(
                        requireContext(),
                        parent,
                        tabLayout,
                        FlightTabData(
                            getString(R.string.prev_day),
                            prices.previousDayPrice.toNumberFormat(),
                            viewModel.departureDate.toDate(DateStrategy.FORMAT2)!!.let {
                                val c = Calendar.getInstance()
                                c.time = it
                                c.add(Calendar.DATE, -1)
                                c.time
                            },
                        )
                    ) {
                            Log.e("TAG", "onChangedScreenState: $it", )
                        viewModel.filterDate.value = it
                        viewModel.state.value = FlightsListVMState.OnDateChange(it)
                    }
                )
                viewModel.tabs.add(
                    FlightTabItem.create(
                        requireContext(),
                        parent,
                        tabLayout,
                        FlightTabData(
                            viewModel.departureDate.formatDate(),
                            prices.previousDayPrice.toNumberFormat(),
                            viewModel.departureDate.toDate(DateStrategy.FORMAT2)!!,
                            true
                        ),
                    ) {
                        Log.e("TAG", "onChangedScreenState: $it", )

                        viewModel.filterDate.value = it
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
                            viewModel.departureDate.toDate(DateStrategy.FORMAT2)!!.let {
                                val c = Calendar.getInstance()
                                c.time = it
                                c.add(Calendar.DATE, 1)
                                c.time
                            },
                        )
                    ) {
                        Log.e("TAG", "onChangedScreenState: $it", )

                        viewModel.filterDate.value = it
                        viewModel.state.value = FlightsListVMState.OnDateChange(it)
                    }
                )
            }
            is FlightsListVMState.OnDateChange -> viewModel.updateFilters()
        }
    }
}