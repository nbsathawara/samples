package com.nbs.newsapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nbs.newsapp.RandomColorGenerator
import com.nbs.newsapp.models.Post
import com.nbs.newsapp.viewmodels.PostViewModel
import com.nbs.newsapp.views.custom.CustomSpacer

@Composable
fun PostScreen(modifier: Modifier = Modifier, postViewModel: PostViewModel) {

    val posts = postViewModel.posts
    LazyColumn(modifier.fillMaxSize()) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}


@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                8.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = RandomColorGenerator.generateRandomColor()
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    8.dp
                ),
        ) {
            Text(
                text = post.title,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
            CustomSpacer(height = 4.dp)
            Text(
                text = post.body,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}