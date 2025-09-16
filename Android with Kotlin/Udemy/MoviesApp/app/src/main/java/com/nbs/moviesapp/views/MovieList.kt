package com.nbs.moviesapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
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
    if (movieViewModel.isLoading)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.FillBounds,
                model = Constants.BASE_IMAGE_URL + movie.posterPath,
                contentDescription = null
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(
                        8.dp
                    ),
                    text = movie.originalTitle,
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(
                            8.dp
                        ),
                    text = movie.overview,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Preview()
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