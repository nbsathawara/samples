package com.nbs.subsriptionapp.data

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import java.util.Locale

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    val dateTimeFormat = SimpleDateFormat("dd.MM.yy hh:mm a", Locale.getDefault())

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