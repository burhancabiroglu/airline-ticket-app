package com.cabir.airlineticketapp.util.network

import android.os.Handler
import android.os.Looper
import com.cabir.airlineticketapp.core.base.IError
import com.cabir.airlineticketapp.core.base.IResponse
import com.cabir.airlineticketapp.core.base.ISuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response


object NetworkHandler {
    suspend fun <T> execute(
        request: suspend () -> Response<T>,
        isAsync: Boolean = false,
        isShowErrorDialog: Boolean = true,
        loadingDelay: Long? = null
    ): IResponse<T> {

        var result: IResponse<T>? = null
        lateinit var response: Response<T>

        val loadingDelayHandler = Handler(Looper.getMainLooper())
        val job = CoroutineScope(Dispatchers.IO).async {
            try {
                response = request.invoke()
            } catch (e: Exception) {
                result = IError(e.message.toString())
            }
        }
        job.await()
        if (checkNetworkError(result)) return result!!
        result = getResult(response, isShowErrorDialog)
        return result!!
    }

    private fun <T> checkNetworkError(result: IResponse<T>?): Boolean =  (result != null && result is IError)

    private fun <T> getResult(response: Response<T>, isShowErrorDialog: Boolean): IResponse<T> {
        return when (response.code()) {
            200 -> handleSuccess(response, isShowErrorDialog)
            else -> handleApiError(response, isShowErrorDialog)
        }
    }

    private fun <T> handleSuccess(response: Response<T>, isShowErrorDialog: Boolean): IResponse<T> {
        response.body()?.let {
            return ISuccess(it)
        } ?: return handleApiError(response, isShowErrorDialog)
    }

    private fun <T> handleApiError(
        response: Response<T>,
        isShowErrorDialog: Boolean
    ): IError {
        return IError(
            response.message(),
            response.code()
        )
    }
}