package com.cabir.airlineticketapp.data.model.price

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PricePair(
    @SerializedName("previous_day_price")
    val previousDayPrice: Int,
    @SerializedName("next_day_price")
    val nextDayPrice: Int
): Parcelable