package com.cabir.airlineticketapp.data.model.search.parameters

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.search.location.Location
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchParameters(
    @SerializedName("request_id")
    val requestId: String,
    val origin: Location,
    val destination: Location,
    @SerializedName("passenger_count")
    val passengerCount: Int,
    @SerializedName("is_one_way")
    val isOneWay: Boolean,
    @SerializedName("flight_class")
    val flightClass: String
): Parcelable