package com.nbs.moviesapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nbs.moviesapp.data.Constants
import com.nbs.moviesapp.models.Movie
import com.nbs.moviesapp.viewmodels.MovieViewModel
import com.nbs.moviesapp.views.custom.CustomSpacer

@Composable
fun MovieList(modifier: Modifier = Modifier, movieViewModel: MovieViewModel) {
    val movies by movieViewModel.movies.collectAsState()
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = Constants.BASE_IMAGE_URL + movie.posterPath,
                contentDescription = null
            )

            CustomSpacer(width = 8.dp)

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = movie.originalTitle)
                CustomSpacer(height = 8.dp)
                Text(text = movie.overview)
            }
        }
    }
}


@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        Movie(
            1470086, "Honey Don't!",
            "Honey O'Donahue, a small-town private investigator, delves into a series of strange deaths tied to a mysterious church.",
            "en",
            "/fJm3kmd9NLZWypMas7g34oNFgbk.jpg",
            70.23
        )
    )
}