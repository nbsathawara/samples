package com.nbs.chatroomapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    val msgDateFormat = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
    val msgTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
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

fun Long.toChatDateTime()
        : String {

    val curDate = Calendar.getInstance(Locale.getDefault())
    val msgDate = Calendar.getInstance()
    msgDate.timeInMillis = this

    if (curDate.get(Calendar.DATE) == msgDate.get(Calendar.DATE)) {
        return "Today " + Constants.msgTimeFormat.format(this)
    } else if (curDate.get(Calendar.DATE) - msgDate.get(Calendar.DATE) == 1) {
        return "Yesterday " + Constants.msgTimeFormat.format(this)
    } else {
        return Constants.msgDateFormat.format(msgDate) + " " +
                Constants.msgTimeFormat.format(msgDate)
    }
}
