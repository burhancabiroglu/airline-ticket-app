package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class BaggageItem(
    val paxType: String,
    val allowance: Int,
    val part: Int,
    val unit: String,
    val type: String,
    val small: Boolean
): Parcelable