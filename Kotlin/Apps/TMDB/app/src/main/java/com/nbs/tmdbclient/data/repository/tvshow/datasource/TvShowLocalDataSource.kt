package com.nbs.tmdbclient.data.repository.tvshow.datasource
import com.nbs.tmdbclient.data.model.tvshow.TvShow

interface TvShowLocalDataSource {

    suspend fun getTvShowsFromDB(): List<TvShow>

    suspend fun saveTvShowToDB(tvShows: List<TvShow>)

    suspend fun clearAll()
}