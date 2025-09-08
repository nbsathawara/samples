package com.nbs.shoppinglist.network

import com.nbs.shoppinglist.services.GeocodingAPIService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val BASE_URL = "https://maps.googleapis.com/"

    fun create(): GeocodingAPIService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(GeocodingAPIService::class.java)
    }
}