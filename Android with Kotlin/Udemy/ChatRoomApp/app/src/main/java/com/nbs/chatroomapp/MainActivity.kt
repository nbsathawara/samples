package com.nbs.chatroomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.chatroomapp.ui.theme.ChatRoomAppTheme
import com.nbs.chatroomapp.viewmodels.account.AuthViewModel
import com.nbs.chatroomapp.views.account.SignInScreen
import com.nbs.chatroomapp.views.account.SignUpScreen
import com.nbs.chatroomapp.views.chat.ChatRoomListScreen
import com.nbs.subsriptionapp.data.Screens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatRoomAppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    Navigation()
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController, startDestination = Screens.SignInScreen.name) {
        composable(Screens.SignUpScreen.name) {
            SignUpScreen(
                viewModel = authViewModel,
                navigateToSingIn = {
                    navController.navigate(Screens.SignInScreen.name) {
                        popUpTo(Screens.SignUpScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onSignUpSuccess = {
                    navController.navigate(Screens.ChatRoomScreen.name) {
                        popUpTo(Screens.SignUpScreen.name) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screens.SignInScreen.name) {
            SignInScreen(
                viewModel = authViewModel,
                navigateToSignUp = {
                    navController.navigate(Screens.SignUpScreen.name) {
                        popUpTo(Screens.SignInScreen.name) {
                            inclusive = true
                        }
                    }
                },
                onSignInSuccess = {
                    navController.navigate(Screens.ChatRoomScreen.name) {
                        popUpTo(Screens.SignInScreen.name) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screens.ChatRoomScreen.name) {
            ChatRoomListScreen()
        }
    }
}
