package com.nbs.moviesapp.views.custom

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    navIcon: (@Composable () -> Unit),
    actionIcons: (
    @Composable () -> Unit)
) {
    TopAppBar(
        modifier = Modifier.shadow(elevation = 4.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(
                title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = navIcon,
        actions = {
            IconButton({

            }) {
                actionIcons()
            }
        }
    )
}

@Preview
@Composable
fun AppBarPreview() {
    AppBar(
        title = "SignUp",
        navIcon = {},
        actionIcons = {}
    )
}