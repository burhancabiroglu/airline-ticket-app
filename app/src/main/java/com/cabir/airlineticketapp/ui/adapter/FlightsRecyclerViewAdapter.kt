package com.cabir.airlineticketapp.ui.adapter

import android.annotation.SuppressLint
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
            val splitArr =  item.price.split(",")
            if (splitArr.isNotEmpty()) {
                binding.priceMajorTextView.text = item.price.split(",")[0]
                binding.priceMinorTextView.text = item.price.split(",")[1]
            }
            else binding.priceMajorTextView.text= item.price
            binding.luggageTextView.text = if(item.baggage) item.baggageAllowance.toString()+" kg/kişi" else "El Bagajı"

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

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(collection: Collection<FlightItem>){
        list = collection
        notifyDataSetChanged()
    }
}