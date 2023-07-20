package com.example.smack.ui.utilities

import android.content.Context
import android.content.SharedPreferences
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import java.nio.channels.Channel

class SharedPrefs(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    val IS_LOGGED_IN = "isLoggedIn"
    val AUTH_TOKEN = "authToken"
    val USER_ID = "userId"
    val USER_NAME = "userName"
    val USER_EMAIL = "userEmail"
    val USER_AVATAR = "userAvatar"
    val USER_AVATAR_COLOR = "userAvatarColor"
    val CHANNEL = "channels"

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")!!
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var userId: String
        get() = prefs.getString(USER_ID, "")!!
        set(value) = prefs.edit().putString(USER_ID, value).apply()

    var userName: String
        get() = prefs.getString(USER_NAME, "")!!
        set(value) = prefs.edit().putString(USER_NAME, value).apply()

    var userEmail: String
        get() = prefs.getString(USER_EMAIL, "")!!
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()

    var userAvatar: String
        get() = prefs.getString(USER_AVATAR, "")!!
        set(value) = prefs.edit().putString(USER_AVATAR, value).apply()

    var userAvatarColor: String
        get() = prefs.getString(USER_AVATAR_COLOR, "")!!
        set(value) = prefs.edit().putString(USER_AVATAR_COLOR, value).apply()

    var channels: String
        get() = prefs.getString(CHANNEL, "")!!
        set(value) = prefs.edit().putString(CHANNEL, value).apply()

    val requestQueue: RequestQueue = Volley.newRequestQueue(context)


}