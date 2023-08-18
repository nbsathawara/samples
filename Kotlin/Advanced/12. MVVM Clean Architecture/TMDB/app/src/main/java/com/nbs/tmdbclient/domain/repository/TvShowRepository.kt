package com.nbs.tmdbclient.domain.repository

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow

interface TvShowRepository {

    suspend fun  getTvShows():List<TvShow>?

    suspend fun  updateTvShows():List<TvShow>?
}