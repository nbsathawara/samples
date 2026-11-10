package com.nbs.dailypulse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.dailypulse.articles.ArticleScreen
import com.nbs.dailypulse.views.AboutScreen
import org.koin.compose.KoinContext

object Routes {
    const val ARTICLES = "articles"
    const val ABOUT = "about"
}

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.ARTICLES
                ) {
                    composable(Routes.ARTICLES) {
                        ArticleScreen(
                            onAboutClick = { navController.navigate(Routes.ABOUT) }
                        )
                    }
                    composable(Routes.ABOUT) {
                        AboutScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
