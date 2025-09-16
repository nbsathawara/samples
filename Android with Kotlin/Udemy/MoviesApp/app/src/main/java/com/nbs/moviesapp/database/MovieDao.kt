package com.nbs.moviesapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbs.moviesapp.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: Movie)

    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie?
}