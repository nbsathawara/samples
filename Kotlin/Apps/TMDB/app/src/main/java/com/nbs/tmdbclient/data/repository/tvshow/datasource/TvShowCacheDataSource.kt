package com.nbs.tmdbclient.data.repository.tvshow.datasource

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow

interface TvShowCacheDataSource {

    suspend fun saveTvShowToCache(tvShows: List<TvShow>)

    suspend fun getTvShowsFromCache(): List<TvShow>
}