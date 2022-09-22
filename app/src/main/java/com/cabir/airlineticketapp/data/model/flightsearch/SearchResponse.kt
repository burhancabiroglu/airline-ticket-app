package com.cabir.airlineticketapp.data.model.flightsearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchResponse(
    val data: FlightSearch
): Parcelable