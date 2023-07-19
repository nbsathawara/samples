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
    val USER_EMAIL = "userEmail"
    val CHANNEL = "channels"

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")!!
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var userEmail: String
        get() = prefs.getString(USER_EMAIL, "")!!
        set(value) = prefs.edit().putString(USER_EMAIL, value).apply()

    var channels: String
        get() = prefs.getString(CHANNEL, "")!!
        set(value) = prefs.edit().putString(CHANNEL, value).apply()

    val requestQueue: RequestQueue = Volley.newRequestQueue(context)


}