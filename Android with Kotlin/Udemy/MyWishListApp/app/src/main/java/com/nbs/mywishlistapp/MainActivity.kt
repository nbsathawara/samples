package com.nbs.mywishlistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.mywishlistapp.data.Screens
import com.nbs.mywishlistapp.ui.theme.MyWishListAppTheme
import com.nbs.mywishlistapp.viewmodels.WishViewModel
import com.nbs.mywishlistapp.views.AddWishView
import com.nbs.mywishlistapp.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWishListAppTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController: NavHostController = rememberNavController()
    val wishViewModel: WishViewModel = viewModel()
    Navigation(navController, wishViewModel)
}

@Composable
fun Navigation(navController: NavHostController, wishViewModel: WishViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.name
    ) {
        composable(Screens.HomeScreen.name) {
            HomeView(navController, wishViewModel)
        }
        composable(Screens.AddWishScreen.name + "/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id")?.toLong()
            AddWishView(
                id = id ?: 0,
                navController = navController,
                viewModel = wishViewModel
            )
        }
    }
}