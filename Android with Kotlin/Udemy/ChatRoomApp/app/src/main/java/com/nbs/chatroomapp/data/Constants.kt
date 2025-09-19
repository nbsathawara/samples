package com.nbs.chatroomapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    val msgDateTimeFormat = SimpleDateFormat("MMM dd yyyy hh:mm a", Locale.getDefault())

    //Firestore
    const val usersCollection = "users"
    const val chatRoomsCollection = "chat_rooms"
    const val messagesCollection = "messages"
    const val messagesTimestamp = "timestamp"

    //icons
    val backIcon = Icons.AutoMirrored.Default.ArrowBack
}

enum class Screens {
    SignUpScreen, SignInScreen, ChatRoomListScreen, ChatRoomScreen
}

enum class HttpStatus {
    Success, Error, Loading
}

//Extensions
fun String.toLowerUnderScore(): String = replace(" ", "_").lowercase()

fun Long.toDateTime(format: SimpleDateFormat = Constants.msgDateTimeFormat)
        : String = format.format(Date(this))
