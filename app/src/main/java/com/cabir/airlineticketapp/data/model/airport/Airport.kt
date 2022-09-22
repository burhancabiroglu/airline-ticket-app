package com.cabir.airlineticketapp.data.model.airport

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Airport(
    val id: String,
    val slug: String,
    @SerializedName("airport_name")
    val airportName:String,
    @SerializedName("city_code")
    val cityCode: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("city_slug")
    val citySlug:String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("country_name")
    val countryName: String
): Parcelable