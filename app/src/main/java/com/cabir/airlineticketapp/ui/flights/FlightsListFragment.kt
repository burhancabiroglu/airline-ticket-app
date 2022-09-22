package com.cabir.airlineticketapp.ui.flights

import androidx.fragment.app.viewModels
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.core.base.BaseFragment
import com.cabir.airlineticketapp.databinding.FragmentFlightsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightsListFragment: BaseFragment<FlightsListViewModel,FragmentFlightsListBinding>() {
    override val viewModel: FlightsListViewModel by viewModels()
    override val layoutRes: Int  = R.layout.fragment_flights_list

    override fun initUI() {
       viewModel.getData()
    }
}