package com.nbs.subsriptionapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    //icons
    val backIcon = Icons.AutoMirrored.Default.ArrowBack
}

enum class Screens {
    SignUpScreen, SignInScreen, ChatRoomScreen
}

enum class HttpStatus {
    Success, Error, Loading
}

//Extensions
//fun User.reset(): Unit {
//}