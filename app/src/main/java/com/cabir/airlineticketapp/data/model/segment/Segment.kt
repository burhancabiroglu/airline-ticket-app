package com.cabir.airlineticketapp.data.model.segment

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Segment(
    @SerializedName("departure_datetime")
    val departureDatetime: DateTimeData,
    @SerializedName("arrival_datetime")
    val arrivalDatetime: DateTimeData,
    @SerializedName("flight_number")
    val flightNumber: String,
    val origin: String,
    val destination: String,
    @SerializedName("marketing_airline")
    val marketingAirline: String,
    @SerializedName("operating_airline")
    val operatingAirline: String
): Parcelable

