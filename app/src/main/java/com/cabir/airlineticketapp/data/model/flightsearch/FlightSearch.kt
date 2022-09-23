package com.cabir.airlineticketapp.data.model.flightsearch

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.airline.Airline
import com.cabir.airlineticketapp.data.model.flights.Flights
import com.cabir.airlineticketapp.data.model.price.PriceHistory
import com.cabir.airlineticketapp.data.model.search.parameters.SearchParameters
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightSearch(
    @SerializedName("search_parameters")
    val searchParameters: SearchParameters,
    val flights: Flights,
    val airlines: ArrayList<Airline>,
    @SerializedName("price_history")
    val priceHistory: PriceHistory,
): Parcelable