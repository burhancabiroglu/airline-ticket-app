package com.cabir.airlineticketapp.core.module

import android.content.Context
import android.util.Log
import com.cabir.airlineticketapp.BuildConfig
import com.cabir.airlineticketapp.data.service.ApiService
import com.cabir.airlineticketapp.util.sslparser.UnsafeOkHttpClient
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.logicbase.mockfit.MockFitConfig
import ir.logicbase.mockfit.MockFitInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://mock.codes/")
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context,endPoint: EndPoint): OkHttpClient {
        val builder = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        builder.addInterceptor(provideMockFitInterceptor(context,endPoint))
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }


    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideMockFitInterceptor(@ApplicationContext context: Context,endPoint: EndPoint) = MockFitInterceptor(
        bodyFactory = { input -> context.resources.assets.open(input) },
        logger = { tag, message -> Log.d(tag, message) },
        baseUrl = endPoint.url,
        requestPathToJsonMap =  MockFitConfig.REQUEST_TO_JSON,
        mockFilesPath = MOCK_FILES_PATH,
        mockFitEnable = true,
        apiEnableMock = false,
        apiIncludeIntoMock = arrayOf("[GET] search"),
        apiExcludeFromMock = arrayOf(),
        apiResponseLatency = 500L
    )

    companion object {
        private const val MOCK_FILES_PATH = "mock_json"
        private const val CONNECT_TIMEOUT = 20L
        private const val WRITE_TIMEOUT = 20L
        private const val READ_TIMEOUT = 30L
    }

}

data class EndPoint(val url: String)