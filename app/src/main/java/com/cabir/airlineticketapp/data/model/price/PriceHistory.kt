package com.cabir.airlineticketapp.data.model.price

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceHistory(
    val departure: PricePair
): Parcelable