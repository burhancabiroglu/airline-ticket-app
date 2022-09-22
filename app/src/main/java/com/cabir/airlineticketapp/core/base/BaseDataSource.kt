package com.cabir.airlineticketapp.core.base

import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> performNetworkOperation(call: suspend () -> Response<T>): IResponse<T>{
        try {
            val response = call.invoke()
            if(response.isSuccessful){
                val body = response.body()
                if (body!=null) return ISuccess(_data = body, _code = response.code())
            }
            val errorBody = response.errorBody().toString()
            return IError(_message = errorBody, _code = response.code())
        }
        catch (err: Exception){
            return IError(_message = err.message.toString())
        }
    }
}