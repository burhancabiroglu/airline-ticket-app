package com.cabir.airlineticketapp.data.model.segment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateTimeData(
    val date: String,
    val time: String,
    val timestamp: Int
) : Parcelable