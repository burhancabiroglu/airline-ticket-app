package com.cabir.airlineticketapp.data.repo

import com.cabir.airlineticketapp.data.service.ApiService
import com.cabir.airlineticketapp.util.network.NetworkHandler
import javax.inject.Inject


class ApiRepo @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun search() = NetworkHandler.execute ({apiService.search()})
}