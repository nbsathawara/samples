package com.nbs.latestnews.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class Utils {
    companion object {
        const val logTag = "Latest News Debug"
        const val newsAPIKey = "8f3fddf391094922b79027f42014140d"
        const val baseUrl = "https://newsapi.org/v2/"


        fun isNetworkAvailable(context: Context): Boolean {
            if (context != null) {
                val connectivityManager =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        when {
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                                return true
                            }
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                                return true
                            }
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                                return true
                            }
                        }
                    }
                } else {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        return true
                    }
                }
            }
            return false
        }
    }
}