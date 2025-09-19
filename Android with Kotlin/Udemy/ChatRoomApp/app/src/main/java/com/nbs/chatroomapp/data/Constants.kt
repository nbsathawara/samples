package com.nbs.chatroomapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

object Constants {
    const val appTag = "ChatRoom Custom Tag"

    val msgDateFormat = SimpleDateFormat("MMMM d,yyyy", Locale.getDefault())
    val msgTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    //Firestore
    const val usersCollection = "users"
    const val chatRoomsCollection = "chat_rooms"
    const val messagesCollection = "messages"

    //icons
    val backIcon = Icons.AutoMirrored.Default.ArrowBack
}

enum class Screens {
    SignUpScreen, SignInScreen, ChatRoomListScreen, ChatRoomScreen
}


//Extensions
fun String.toLowerUnderScore(): String = replace(" ", "_").lowercase()

fun Long.toChatDateTime()
        : String {
    try {
        val curDate = Calendar.getInstance(Locale.getDefault())
        val msgDate = Calendar.getInstance(Locale.getDefault())
        msgDate.timeInMillis = this

        if (curDate.get(Calendar.DATE) == msgDate.get(Calendar.DATE)) {
            return "Today " + Constants.msgTimeFormat.format(this)
        } else if (curDate.get(Calendar.DATE) - msgDate.get(Calendar.DATE) == 1) {
            return "Yesterday " + Constants.msgTimeFormat.format(this)
        }
        return Constants.msgDateFormat.format(msgDate.timeInMillis) + " " +
                Constants.msgTimeFormat.format(msgDate.timeInMillis)

        //return Constants.msgTimeFormat.format(this)
    } catch (e: Exception) {
        return "Invalid date"
    }
}

fun Long.toChatDate()
        : String {
    try {
        val curDate = Calendar.getInstance(Locale.getDefault())
        val msgDate = Calendar.getInstance(Locale.getDefault())
        msgDate.timeInMillis = this

        if (curDate.get(Calendar.DATE) == msgDate.get(Calendar.DATE)) {
            return "Today"
        } else if (curDate.get(Calendar.DATE) - msgDate.get(Calendar.DATE) == 1) {
            return "Yesterday"
        }
        return Constants.msgDateFormat.format(msgDate.timeInMillis)
    } catch (e: Exception) {
        return "Invalid date"
    }
}
