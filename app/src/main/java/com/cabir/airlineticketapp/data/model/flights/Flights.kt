package com.cabir.airlineticketapp.data.model.flights

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.departure.Departure
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flights(
    val departure: ArrayList<Departure>
): Parcelable