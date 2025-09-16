package com.nbs.moviesapp.network

import com.nbs.moviesapp.models.TMDBResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBAPIService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): TMDBResponse

}