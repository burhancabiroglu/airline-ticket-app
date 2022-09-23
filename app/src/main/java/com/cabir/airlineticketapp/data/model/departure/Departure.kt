package com.cabir.airlineticketapp.data.model.departure

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.info.Info
import com.cabir.airlineticketapp.data.model.segment.Segment
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Departure(
    val enuid: String,
    @SerializedName("price_breakdown")
    val priceBreakdown: PriceBreakdown,
    @SerializedName("average_price_breakdown")
    val averagePriceBreakdown: PriceBreakdown,
    val infos: Info,
    val segments: ArrayList<Segment>,
    @SerializedName("different_airline_count")
    val differentAirlineCount: Int
) : Parcelable
