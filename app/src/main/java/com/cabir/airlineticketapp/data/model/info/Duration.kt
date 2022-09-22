package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Duration(
    val day : Int,
    val hour: Int,
    val minute : Int,
    @SerializedName("total_minutes")
    val totalMinutes: Int
): Parcelable