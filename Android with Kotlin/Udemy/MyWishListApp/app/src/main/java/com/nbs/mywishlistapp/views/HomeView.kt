package com.nbs.mywishlistapp.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.nbs.mywishlistapp.custom.BackIcon
import com.nbs.mywishlistapp.custom.NavigationIcon
import com.nbs.mywishlistapp.data.Constants
import com.nbs.mywishlistapp.models.DummyData
import com.nbs.mywishlistapp.ui.theme.Primary

@Composable
fun HomeView() {
    Scaffold(
        topBar = {
            AppBar(
                title = "WishList",
                navIcon = { }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) { it ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(DummyData.wishList) { wish ->
                WishItem(wish, {})
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeViewPreview() {
    HomeView()
}