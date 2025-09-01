package com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource


class TvShowCacheDataSourceImpl : TvShowCacheDataSource {
    private val tvShows = ArrayList<TvShow>()

    override suspend fun saveTvShowToCache(tvShows: List<TvShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
    }

    override suspend fun getTvShowsFromCache(): List<TvShow> {
        return tvShows
    }
}