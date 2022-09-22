package com.cabir.airlineticketapp.util.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.cabir.airlineticketapp.core.base.ILoading
import com.cabir.airlineticketapp.core.base.IResponse
import kotlinx.coroutines.Dispatchers

class NetworkUtil {
    companion object {
        fun <T> performOperation(call: suspend () -> IResponse<T>): LiveData<IResponse<T>> {
            return liveData(Dispatchers.IO) {
                emit(ILoading())
                val networkCall = call.invoke()
                emit(networkCall)
            }
        }
    }
}