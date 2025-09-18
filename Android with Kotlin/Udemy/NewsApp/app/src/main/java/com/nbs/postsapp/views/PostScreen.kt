package com.nbs.postsapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.nbs.postsapp.models.Post
import com.nbs.postsapp.viewmodels.PostViewModel
import com.nbs.postsapp.views.custom.CustomSpacer

@Composable
fun PostScreen(
    modifier: Modifier = Modifier, postViewModel: PostViewModel = hiltViewModel()
) {
    val posts by postViewModel.posts.collectAsState()

    LazyColumn(
        modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}


@Composable
fun PostItem(post: Post) {
    CustomSpacer(height = 4.dp)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 8.dp, vertical = 4.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            //RandomColorGenerator.generateRandomColor()
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
                style = MaterialTheme.typography.titleLarge
            )
            CustomSpacer(height = 4.dp)
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}