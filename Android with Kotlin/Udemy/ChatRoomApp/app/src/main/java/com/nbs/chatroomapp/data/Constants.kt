package com.nbs.subsriptionapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.nbs.chatroomapp.R
import com.nbs.chatroomapp.data.models.User

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
fun User.reset(): Unit {

}