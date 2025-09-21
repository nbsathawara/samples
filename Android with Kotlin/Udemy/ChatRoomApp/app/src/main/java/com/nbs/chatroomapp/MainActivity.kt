package com.nbs.chatroomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.chatroomapp.ui.theme.ChatRoomAppTheme
import com.nbs.chatroomapp.viewmodels.account.AuthViewModel
import com.nbs.chatroomapp.views.account.SignInScreen
import com.nbs.chatroomapp.views.account.SignUpScreen
import com.nbs.chatroomapp.views.chat.ChatRoomListScreen
import com.nbs.chatroomapp.data.Screens
import com.nbs.chatroomapp.data.models.ChatRoom
import com.nbs.chatroomapp.views.chat.ChatRoomScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser by authViewModel.currentUser.collectAsStateWithLifecycle()

    val startDestination =
        if (currentUser == null) Screens.SignInScreen.name
        else Screens.ChatRoomListScreen.name

    NavHost(navController, startDestination = startDestination) {
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
                    navController.navigate(Screens.ChatRoomListScreen.name) {
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
                    navController.navigate(Screens.ChatRoomListScreen.name) {
                        popUpTo(Screens.SignInScreen.name) {
                            inclusive = true
                        }
                    }
                })
        }
        composable(Screens.ChatRoomListScreen.name) {
            ChatRoomListScreen(
                onChatRoomJoined = { chatRoom ->
                    navController.navigate(
                        Screens.ChatRoomScreen.name
                                + "/${chatRoom.id}/${chatRoom.name}"
                    )
                }
            )
        }
        composable(Screens.ChatRoomScreen.name + "/{id}/{title}") {
            val id = it.arguments?.getString("id")!!
            val title = it.arguments?.getString("title")!!
            ChatRoomScreen(
                id, title,
                onNavigateBack = {
                    navController.popBackStack()
                })
        }
    }
}
