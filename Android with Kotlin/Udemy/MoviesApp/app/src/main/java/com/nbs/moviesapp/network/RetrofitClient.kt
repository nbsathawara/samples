package com.nbs.moviesapp.network

import com.nbs.moviesapp.data.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.time.Duration

object RetrofitClient {

    val tmdbApiService: TMDBAPIService by lazy {
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS) // Set connect timeout to 30 seconds
            .readTimeout(5, TimeUnit.SECONDS)    // Set read timeout to 20 seconds
            .writeTimeout(5, TimeUnit.SECONDS)   // Set write timeout to 25 seconds
            .callTimeout(5, TimeUnit.SECONDS)    // Set call timeout to 15 seconds
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TMDBAPIService::class.java)
    }

}