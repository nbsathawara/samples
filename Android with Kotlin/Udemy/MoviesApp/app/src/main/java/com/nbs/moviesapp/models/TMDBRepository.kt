package com.nbs.moviesapp.models

import android.content.Context
import com.nbs.moviesapp.database.AppDatabase
import com.nbs.moviesapp.network.RetrofitClient
import kotlinx.coroutines.flow.Flow

class TMDBRepository(val context: Context) {

    suspend fun getOnlineMovies(apiKey: String, page: Int = 1): List<Movie> {
        return RetrofitClient.tmdbApiService.getPopularMovies(apiKey, page).results
    }

    suspend fun getOfflineMovies(): Flow<List<Movie>> {
        return AppDatabase.getInstance(context).movieDao().getAllMovies()
    }

    suspend fun insertMovies(movies: List<Movie>) {
        AppDatabase.getInstance(context).movieDao().insertMovies(movies)
    }
}