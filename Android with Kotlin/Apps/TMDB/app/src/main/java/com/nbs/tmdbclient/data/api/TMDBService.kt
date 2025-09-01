package com.nbs.tmdbclient.data.api

import com.nbs.tmdbclient.data.model.artist.ArtistList
import com.nbs.tmdbclient.data.model.movie.MovieList
import com.nbs.tmdbclient.data.model.tvshow.TvShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(value = "api_key") apiKey: String): Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query(value = "api_key") apiKey: String): Response<TvShowList>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query(value = "api_key") apiKey: String): Response<ArtistList>
}