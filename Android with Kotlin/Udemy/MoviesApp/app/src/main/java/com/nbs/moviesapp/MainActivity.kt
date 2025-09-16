package com.nbs.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nbs.moviesapp.models.TMDBRepository
import com.nbs.moviesapp.ui.theme.MoviesAppTheme
import com.nbs.moviesapp.viewmodels.MovieViewModel
import com.nbs.moviesapp.viewmodels.MovieViewModelFactory
import com.nbs.moviesapp.views.MovieList
import com.nbs.moviesapp.views.custom.AppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tmdbRepository = TMDBRepository()
        val movieViewModel: MovieViewModel = ViewModelProvider(
            this,
            MovieViewModelFactory(tmdbRepository)
        )[MovieViewModel::class.java]

        setContent {
            MoviesAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBar(
                            title = "Movies",
                            navIcon = {},
                            actionIcons = {}
                        )
                    }) { innerPadding ->
                    MovieList(
                        modifier = Modifier.padding(innerPadding),
                        movieViewModel
                    )
                }
            }
        }
    }
}