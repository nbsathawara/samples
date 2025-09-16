package com.nbs.moviesapp.models

import com.nbs.moviesapp.network.RetrofitClient

class TMDBRepository {

    suspend fun getPopularMovies(apiKey: String, page: Int = 1): List<Movie> {
        return RetrofitClient.tmdbApiService.getPopularMovies(apiKey, page).results
    }

}