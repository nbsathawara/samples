package com.nbs.postsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nbs.postsapp.ui.theme.PotsAppTheme
import com.nbs.postsapp.viewmodels.PostViewModel
import com.nbs.postsapp.views.PostScreen
import com.nbs.postsapp.views.custom.AppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PotsAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppBar(
                            title = "Posts App",
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
