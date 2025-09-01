package com.nbs.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nbs.tmdbclient.data.model.tvshow.TvShow

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(movies: List<TvShow>)

    @Query("delete from popular_tvShows")
    suspend fun deleteAllTvShows()

    @Query("select * from popular_tvShows")
    suspend fun getAllTvShows(): List<TvShow>
}