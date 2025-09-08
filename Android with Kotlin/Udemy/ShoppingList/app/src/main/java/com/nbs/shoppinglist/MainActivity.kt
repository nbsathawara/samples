package com.nbs.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.nbs.shoppinglist.data.Screens
import com.nbs.shoppinglist.ui.theme.ShoppingListTheme
import com.nbs.shoppinglist.utils.LocationUtils
import com.nbs.shoppinglist.utils.PermissionUtils
import com.nbs.shoppinglist.viewmodels.LocationViewModel
import com.nbs.shoppinglist.views.LocationSelectionScreen
import com.nbs.shoppinglist.views.ShoppingList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        MyApp()
                    }
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        val context = LocalContext.current
        val navController = rememberNavController()
        val viewModel: LocationViewModel = viewModel()
        val permissionUtils = PermissionUtils()
        val locationUtils = LocationUtils()

        NavHost(
            navController = navController,
            startDestination = Screens.ShoppingListScreen.name
        ) {
            composable(Screens.ShoppingListScreen.name) {
                ShoppingList(
                    context,
                    permissionUtils,
                    locationUtils,
                    navController,
                    viewModel,
                    viewModel.address.value.firstOrNull()?.formatted_address ?: "No Address!!"
                )
            }
            dialog(Screens.LocationSelectionScreen.name) { backstack ->
                viewModel.location.value?.let { location ->
                    LocationSelectionScreen(
                        location,
                        { locationData ->
                            viewModel
                                .fetchAddress("${locationData.lat},${locationData.lng}")
                            navController.popBackStack()
                        })
                }
            }
        }
    }

}
