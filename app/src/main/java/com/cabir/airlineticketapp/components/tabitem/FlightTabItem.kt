package com.cabir.airlineticketapp.components.tabitem

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cabir.airlineticketapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab

class FlightTabItem private constructor(
    private val context: Context,
    private val parent: ViewGroup,
    private val tabLayout: TabLayout,
    private val labels: Pair<String,String>,
    private val isToday:Boolean = false
) : RecyclerView.ViewHolder(
    LayoutInflater.from(context).inflate(R.layout.fragment_flight_tab_item, parent, false)
), OnTabSelectedListener {

    private val amountTextView: AppCompatTextView = itemView.findViewById(R.id.amountTextView)
    private val dayTextView: AppCompatTextView = itemView.findViewById(R.id.dayTextView)
    private val dateImageView = itemView.findViewById<ImageView>(R.id.dateImageView)
    private val tab: Tab = tabLayout.newTab()

    init {
        tab.customView = itemView
        tabLayout.addTab(tab)
        dayTextView.text = labels.first
        if (tab.isSelected) selected()
        if(isToday) {
            dateImageView.visibility = View.VISIBLE
            tab.select()
            selected()
        }
        amountTextView.text = labels.second.plus(" TL")
        tabLayout.addOnTabSelectedListener(this)
    }

    private fun selected() {
        val green = ContextCompat.getColor(context, R.color.emerald)
        dayTextView.setTextColor(green)
        amountTextView.setTextColor(green)
        dateImageView.imageTintList = ColorStateList.valueOf(green)
    }

    private fun unselected() {
        val nevada = ContextCompat.getColor(context, R.color.nevada)
        dayTextView.setTextColor(nevada)
        amountTextView.setTextColor(nevada)
        dateImageView.imageTintList = ColorStateList.valueOf(nevada)
    }

    companion object {
        fun create(
            context: Context,
            parent: ViewGroup,
            tabLayout: TabLayout,
            labels: Pair<String,String>,
            isToday:Boolean = false
        ): FlightTabItem = FlightTabItem(context, parent, tabLayout, labels,isToday)

    }

    override fun onTabSelected(tab: Tab?) = if(tab?.position == this.tab.position) selected() else Unit
    override fun onTabUnselected(tab: Tab?) = unselected()
    override fun onTabReselected(tab: Tab?) {}
}