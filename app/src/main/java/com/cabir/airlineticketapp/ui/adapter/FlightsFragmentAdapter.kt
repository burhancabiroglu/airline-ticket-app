package com.cabir.airlineticketapp.ui.adapter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cabir.airlineticketapp.R

class FlightsFragmentAdapter(
    private val list: Collection<Any>,
    fa: FragmentActivity
): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = list.count()

    override fun createFragment(position: Int): Fragment = FlightTabItem(list.elementAt(position))

    class FlightTabItem(private val element: Any) : Fragment(R.layout.fragment_flight_tab_item) {
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val amountTextView: TextView = view.findViewById(R.id.amountTextView)
            val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        }
    }
}