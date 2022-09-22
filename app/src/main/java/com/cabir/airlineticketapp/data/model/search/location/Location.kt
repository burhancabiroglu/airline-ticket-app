package com.cabir.airlineticketapp.data.model.search.location

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val label: String,
    val id: String,
    @SerializedName("is_city")
    val isCity: Boolean,
    @SerializedName("city_name")
    val cityName: String,
    val slug: String,
    @SerializedName("airport_name")
    val airportName: String,
    @SerializedName("country_name")
    val countryName: String,
    @SerializedName("country_code")
    val countryCode: String
):Parcelable
