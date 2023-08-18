package com.nbs.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityMoviesBinding
import com.nbs.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding

    @Inject
    lateinit var movieViewModelFactory: MovieViewModel.MovieViewModelFactory
    private val movieViewModel: MovieViewModel by viewModels { movieViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        (application as Injector).createMovieSubComponent()
            .inject(this)

        val resLiveData = movieViewModel.getMovies()
        resLiveData.observe(this, Observer {
            Log.i(Utils.logTagName, it.toString())
        })

    }
}