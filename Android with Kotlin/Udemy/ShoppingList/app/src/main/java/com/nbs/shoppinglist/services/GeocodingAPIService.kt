package com.nbs.shoppinglist.services

import com.nbs.shoppinglist.models.GeocodingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodingAPIService {

    @GET("maps/api/geocode/json")
    suspend fun getAddressFromCoordinates(
        @Query("latlng") latlng: String,
        @Query("key") key: String
    ): GeocodingResponse
}