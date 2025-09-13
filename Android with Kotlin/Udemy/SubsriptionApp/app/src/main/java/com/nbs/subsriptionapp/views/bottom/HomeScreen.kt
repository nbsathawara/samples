package com.nbs.subsriptionapp.views.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.nbs.subsriptionapp.models.allSubscriptions
import com.nbs.subsriptionapp.models.randomGenres

@Composable
fun HomeScreen(isBrowse: Boolean = false) {
    val subscriptionGenresMap = mutableMapOf<Subscription, List<Genre>>()

    val subscriptions = if (isBrowse)
        allSubscriptions
    else
        allSubscriptions.filter { it.isSubscribed }

    subscriptions.sortedBy { it.title }.forEach { subscription ->
        subscriptionGenresMap[subscription] = allGenres.shuffled()
            .take(allGenres.size / 2)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        subscriptionGenresMap.forEach { (subscription, genres) ->
            stickyHeader {
                SubscriptionItem(subscription)
                LazyRow() {
                    items(genres) { genre ->
                        GenreItem(genre)
                    }
                }
            }
        }
    }
}

@Composable
fun SubscriptionItem(subscription: Subscription) {
    Card(
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(subscription.iconId),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(32.dp)
            )
            CustomSpacer(width = 8.dp)
            Text(
                subscription.title,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun GenreItem(genre: Genre) {
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
fun HomeScreenPreview() {
    HomeScreen()
}