package com.cabir.airlineticketapp.data.model.airline

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Airline(
    val code: String,
    val name: String,
    val slug: String,
    val image: String
): Parcelable