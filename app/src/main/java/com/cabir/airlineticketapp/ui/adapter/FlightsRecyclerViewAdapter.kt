package com.cabir.airlineticketapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.databinding.ItemFlightInfoBinding

class FlightsRecyclerViewAdapter(private val items: Collection<Any>) :
    RecyclerView.Adapter<FlightsRecyclerViewAdapter.FlightsViewHolder>() {

    inner class FlightsViewHolder(private val binding: ItemFlightInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Any) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemFlightInfoBinding>(
            inflater,
            R.layout.item_flight_info,
            parent,
            false
        )
        return FlightsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bind(items.elementAt(position))
    }

    override fun getItemCount(): Int = items.count()
}