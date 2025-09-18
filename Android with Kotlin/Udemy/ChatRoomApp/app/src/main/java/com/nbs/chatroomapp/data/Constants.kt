package com.nbs.chatroomapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    //Firestore
    const val usersCollection = "users"
    const val chatRoomsCollection = "chat_rooms"
    const val messagesCollection = "messages"

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
fun String.toLowerUnderScore(): String = replace(" ", "_").lowercase()
