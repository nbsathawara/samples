package com.nbs.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.recipeapp.data.Constants
import com.nbs.recipeapp.models.Category
import com.nbs.recipeapp.ui.theme.RecipeAppTheme
import com.nbs.recipeapp.views.RecipeDetailsScreen
import com.nbs.recipeapp.views.RecipeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Constants.Routes.RECIPE_SCREEN.name
    ) {
        composable(Constants.Routes.RECIPE_SCREEN.name) {
            RecipeScreen(modifier, {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Constants.Routes.RECIPE_DETAILS_SCREEN.name)
            })
        }
        composable(Constants.Routes.RECIPE_DETAILS_SCREEN.name) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category")
                    ?: Category("", "", "", "")
            RecipeDetailsScreen(modifier, category)
        }
    }
}
