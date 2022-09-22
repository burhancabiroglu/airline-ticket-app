package com.cabir.airlineticketapp.data.model.info

import android.os.Parcelable
import com.cabir.airlineticketapp.data.model.departure.BaggageInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(
    @SerializedName("is_reservable")
    val isReservable: Boolean,
    @SerializedName("is_promo")
    val isPromo: Boolean,
    val duration: Duration,
    @SerializedName("baggage_info")
    val baggageInfo: BaggageInfo,
    @SerializedName("is_virtual_interlining")
    val isVirtualInterlining: Boolean,
    @SerializedName("is_business")
    val isBusiness: Boolean,
    @SerializedName("active_warning")
    val activeWarning: Boolean = false,
    @SerializedName("is_mask_required")
    val isMaskRequired: Boolean
): Parcelable
