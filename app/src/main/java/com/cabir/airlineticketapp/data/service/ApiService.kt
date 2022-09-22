package com.cabir.airlineticketapp.data.service

import com.cabir.airlineticketapp.data.model.flightsearch.SearchResponse
import ir.logicbase.mockfit.Mock
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @Mock("enuygun_flight_search.json")
    @GET("search")
    suspend fun search(): Response<SearchResponse>
}