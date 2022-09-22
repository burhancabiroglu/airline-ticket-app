package com.cabir.airlineticketapp.data.service

import com.cabir.airlineticketapp.data.model.flightsearch.SearchResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/search")
    suspend fun search(): Response<SearchResponse>
}