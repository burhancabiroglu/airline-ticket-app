package com.cabir.airlineticketapp.data.remote

import com.cabir.airlineticketapp.core.base.BaseDataSource
import com.cabir.airlineticketapp.data.service.ApiService

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource(){
    suspend fun search() = performNetworkOperation { apiService.search() }
}