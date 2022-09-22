package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.info.BaggageItem
import com.cabir.airlineticketapp.data.model.info.CarryOn
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaggageInfo(
    val carryOn: CarryOn,
    val firstBaggageCollection: ArrayList<BaggageItem>
): Parcelable
