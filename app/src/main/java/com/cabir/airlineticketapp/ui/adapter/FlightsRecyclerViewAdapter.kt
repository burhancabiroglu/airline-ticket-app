package com.cabir.airlineticketapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cabir.airlineticketapp.R
import com.cabir.airlineticketapp.databinding.ItemFlightInfoBinding
import com.cabir.airlineticketapp.util.extension.parseUnicode

class FlightsRecyclerViewAdapter(private var list: Collection<FlightItem>) :
    RecyclerView.Adapter<FlightsRecyclerViewAdapter.FlightsViewHolder>() {

    inner class FlightsViewHolder(private val binding: ItemFlightInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlightItem) {
            item.marketingAirline.name = item.marketingAirline.name.parseUnicode()
            item.operatingAirline.name = item.operatingAirline.name.parseUnicode()
            binding.item = item
        }
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
        holder.bind(list.elementAt(position))
    }

    override fun getItemCount(): Int = list.count()

    fun updateData(collection: Collection<FlightItem>){
        list = collection
        notifyItemRangeChanged(0,collection.count())
    }
}