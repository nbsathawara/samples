package com.nbs.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nbs.newsapp.ui.theme.NewsAppTheme
import com.nbs.newsapp.viewmodels.PostViewModel
import com.nbs.newsapp.views.PostScreen
import com.nbs.newsapp.views.custom.AppBar

class MainActivity : ComponentActivity() {
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBar(
                            title = "News App",
                            navIcon = {},
                            actionIcons = {}
                        )
                    }) { innerPadding ->
                    PostScreen(
                        modifier = Modifier.padding(innerPadding),
                        postViewModel
                    )
                }
            }
        }
    }
}
