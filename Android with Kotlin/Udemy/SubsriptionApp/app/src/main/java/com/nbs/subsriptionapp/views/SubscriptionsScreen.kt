package com.nbs.subsriptionapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.subsriptionapp.custom.CustomSpacer
import com.nbs.subsriptionapp.models.Subscription
import com.nbs.subsriptionapp.models.allSubscriptions

@Composable
fun SubscriptionsScreen() {
    var showMySubscriptions by remember { mutableStateOf(false) }
    var showAllSubscriptions by remember { mutableStateOf(false) }

    var iconMySubscriptions = Icons.AutoMirrored.Filled.KeyboardArrowRight
    var iconAllSubscriptions = Icons.AutoMirrored.Filled.KeyboardArrowRight
    if (showMySubscriptions)
        iconMySubscriptions = Icons.Default.KeyboardArrowDown
    if (showAllSubscriptions)
        iconAllSubscriptions = Icons.Default.KeyboardArrowDown

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "My Subscriptions",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                CustomSpacer(width = 8.dp)
                IconButton({
                    showMySubscriptions = !showMySubscriptions
                    showAllSubscriptions = false
                }) {
                    Icon(iconMySubscriptions, "")
                }
            }
        }
        if (showMySubscriptions)
            LazyColumn() {
                val mySubscriptions = allSubscriptions.filter {
                    it.isSubscribed
                }
                items(mySubscriptions) { subscription ->
                    SubscriptionItem(subscription)
                }
            }
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "All Subscriptions",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                CustomSpacer(width = 8.dp)
                IconButton({
                    showAllSubscriptions = !showAllSubscriptions
                    showMySubscriptions = false
                }) {
                    Icon(iconAllSubscriptions, "")
                }
            }
        }
        if (showAllSubscriptions)
            LazyColumn() {
                items(allSubscriptions) { subscription ->
                    SubscriptionItem(subscription)
                }
            }

    }
}

@Composable
fun SubscriptionItem(subscription: Subscription) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Icon(
                painter = painterResource(id = subscription.iconId),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(36.dp)
            )
            CustomSpacer(width = 8.dp)
            Text(
                subscription.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            CustomSpacer(width = 8.dp)
            Text("$ " + subscription.price.toString(), style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
@Preview
fun SubscriptionsScreenPreview() {
    SubscriptionsScreen()
}