package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CarryOn(
    val allowance: Int,
    val type: String,
    val unit: String,
    val part: Int,
    val is_small: Boolean
): Parcelable
