package com.cabir.airlineticketapp.data.model.flightsearch

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.flights.Flights
import com.cabir.airlineticketapp.data.model.price.PriceHistory
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightSearch(
    val flights: Flights,
    @SerializedName("price_history")
    val priceHistory: PriceHistory
): Parcelable