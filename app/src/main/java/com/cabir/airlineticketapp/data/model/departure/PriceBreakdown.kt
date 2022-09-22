package com.cabir.airlineticketapp.data.model.departure

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceBreakdown(
    val base: Int,
    val tax: Int,
    val service: Double,
    @SerializedName("reissue_service")
    val reissueService: Number,
    val total: Double,
    val currency: String,
    val discount: Double,
    @SerializedName("displayed_currency")
    val displayedCurrency: String,
    @SerializedName("internal_assurance")
    val internalAssurance: Int,
    @SerializedName("extra_fee")
    val extraFee: Int,
    val penalty: Int
): Parcelable
