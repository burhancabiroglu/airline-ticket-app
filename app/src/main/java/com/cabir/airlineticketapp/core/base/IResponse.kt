package com.cabir.airlineticketapp.core.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


sealed class IResponse<out T>(val message: String = "", val data: T? = null, val code: Int = 0) {}

@Parcelize
data class IError(
    private val _message: String = "",
    private val _code: Int = 0
) : IResponse<Nothing>(message = _message,code = _code), Parcelable

@Parcelize
data class ILoading(
    private val _message: String = "",
    private val _code: Int = 0
) : IResponse<Nothing>(message = _message,code = _code), Parcelable

@Parcelize
data class ISuccess<out T> (
    private val _data: @RawValue T?,
    private val _code: Int = 0
) : IResponse<T>(message = "",code = _code, data = _data), Parcelable