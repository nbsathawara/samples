package com.nbs.mywishlistapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nbs.mywishlistapp.custom.CustomAlertDialog
import com.nbs.mywishlistapp.data.Screens
import com.nbs.mywishlistapp.data.models.Wish
import com.nbs.mywishlistapp.viewmodels.WishViewModel

@Composable
fun HomeView(navController: NavController, wishViewModel: WishViewModel) {
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
                onClick = {
                    navController.navigate(Screens.AddWishScreen.name + "/0")
                }) {
                Icon(Icons.Default.Add, contentDescription = "")
            }
        }
    ) { innerPadding ->

        var deleteConfirmDialog by remember { mutableStateOf(false) }
        var wishToDelete by remember { mutableStateOf<Wish?>(null) }
        when {
            deleteConfirmDialog -> {
                CustomAlertDialog(
                    dialogTitle = "Confirmation",
                    dialogText = "Are you sure you want to delete this wish?",
                    confirmText = "Yes",
                    dismissText = "No",
                    icon = Icons.Default.Delete,
                    onDismissRequest = {
                        deleteConfirmDialog = false
                    },
                    onConfirmation = {
                        wishToDelete?.let {
                            wishViewModel.deleteWish(it)
                        }
                        deleteConfirmDialog = false
                        wishToDelete = null
                    })
            }
        }

        val wishList = wishViewModel.getAllWishes.collectAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(wishList.value, key = { it.id }) { wish ->

                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = {
                        if (it == SwipeToDismissBoxValue.EndToStart
                        ) {
                            wishToDelete = wish
                            deleteConfirmDialog = true
                        }
                        false
                    }
                )

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromEndToStart = true,
                    backgroundContent = {
                        when (dismissState.dismissDirection) {
                            SwipeToDismissBoxValue.EndToStart -> {
                                Box(
                                    modifier =
                                        Modifier.padding(
                                            start = 12.dp,
                                            top = 12.dp,
                                            end = 12.dp,
                                            bottom = 4.dp
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remove item",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Red)
                                            .wrapContentSize(Alignment.CenterEnd)
                                            .padding(8.dp),
                                        tint = Color.White
                                    )
                                }
                            }
                            else -> {
                            }
                        }
                    }
                ) {
                    WishItem(wish, {
                        navController.navigate(Screens.AddWishScreen.name + "/${wish.id}")
                    })
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeViewPreview() {
    //HomeView()
}