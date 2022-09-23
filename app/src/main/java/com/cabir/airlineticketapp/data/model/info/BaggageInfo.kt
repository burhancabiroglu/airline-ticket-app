package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaggageInfo(
    val carryOn: CarryOn?,
    val firstBaggageCollection: ArrayList<BaggageItem>?
): Parcelable
