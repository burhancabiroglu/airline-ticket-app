package com.cabir.airlineticketapp.data.repo

import com.cabir.airlineticketapp.data.remote.RemoteDataSource
import com.cabir.airlineticketapp.util.network.NetworkUtil


class ApiRepo(
    private val remoteDataSource: RemoteDataSource,
) {
    fun search() = NetworkUtil.performOperation { remoteDataSource.search() }
}