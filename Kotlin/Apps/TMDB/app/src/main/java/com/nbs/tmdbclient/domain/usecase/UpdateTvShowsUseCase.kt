package com.nbs.tmdbclient.domain.usecase

import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.data.model.tvshow.TvShow
import com.nbs.tmdbclient.domain.repository.MovieRepository
import com.nbs.tmdbclient.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShows()
}