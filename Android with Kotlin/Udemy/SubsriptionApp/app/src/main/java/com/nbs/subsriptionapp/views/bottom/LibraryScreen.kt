package com.nbs.subsriptionapp.views.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.models.Genre
import com.nbs.subsriptionapp.models.Subscription
import com.nbs.subsriptionapp.models.allGenres

@Composable
fun LibraryScreen() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(allGenres) { genre ->
            Genre(genre)
        }
    }
}

@Composable
fun Genre(genre: Genre) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .size(125.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(genre.iconId),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.weight(1f)
            )
            CustomSpacer(height = 4.dp)
            Text(
                text = genre.title,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LibraryScreenPreview() {
    LibraryScreen()
}