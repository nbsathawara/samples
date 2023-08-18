package com.nbs.tmdbclient.data.repository.tvshow.datasourceimpl

import android.util.Log
import com.nbs.tmdbclient.DataUtils
import com.nbs.tmdbclient.data.model.tvshow.TvShow
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.nbs.tmdbclient.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.nbs.tmdbclient.domain.repository.TvShowRepository

class TvShowRepositoryImpl(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowCacheDataSource: TvShowCacheDataSource
) : TvShowRepository {
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newTvShows = getTvShowsFromAPI()

        tvShowLocalDataSource.clearAll()
        tvShowLocalDataSource.saveTvShowToDB(newTvShows)

        tvShowCacheDataSource.saveTvShowToCache(newTvShows)

        return newTvShows
    }

    private suspend fun getTvShowsFromCache(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            tvShows = tvShowCacheDataSource.getTvShowsFromCache()
        } catch (e: java.lang.Exception) {
            Log.i(DataUtils.logTagName, e.message.toString())
        }
        if (tvShows.isNotEmpty()) {
            return tvShows
        } else {
            tvShows = getTvShowsFromDB()
            tvShowCacheDataSource.saveTvShowToCache(tvShows)
            return tvShows
        }
    }

    private suspend fun getTvShowsFromDB(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            tvShows = tvShowLocalDataSource.getTvShowsFromDB()
        } catch (e: java.lang.Exception) {
            Log.i(DataUtils.logTagName, e.message.toString())
        }
        if (tvShows.isNotEmpty()) {
            return tvShows
        } else {
            tvShows = getTvShowsFromAPI()
            return tvShows
        }
    }

    private suspend fun getTvShowsFromAPI(): List<TvShow> {
        lateinit var tvShows: List<TvShow>
        try {
            val response = tvShowRemoteDataSource.getTvShows()
            val body = response.body()
            if (body != null) {
                tvShows = body.tvShows
            }
        } catch (e: java.lang.Exception) {
            Log.i(DataUtils.logTagName, e.message.toString())
        }
        return tvShows
    }
}