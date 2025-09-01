package com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl

import com.nbs.tmdbclient.data.db.MovieDao
import com.nbs.tmdbclient.data.db.TvShowDao
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow
import com.nbs.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowLocalDataSourceImpl(private val tvShowDao: TvShowDao) : TvShowLocalDataSource {

    override suspend fun getTvShowsFromDB(): List<TvShow> {

        return tvShowDao.getAllTvShows()
    }

    override suspend fun saveTvShowToDB(tvShows: List<TvShow>) {
       CoroutineScope(Dispatchers.IO).launch {
           tvShowDao.saveTvShows(tvShows)
       }
    }

    override suspend fun clearAll() {
       CoroutineScope(Dispatchers.IO).launch {
           tvShowDao.deleteAllTvShows()
       }
    }
}