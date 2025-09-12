package com.nbs.subsriptionapp.models

import com.nbs.subsriptionapp.R

data class Subscription(
    val title: String,
    val price: Double,
    val iconId: Int,
    val isSubscribed: Boolean = false
)

val allSubscriptions = listOf<Subscription>(
    Subscription("Apple TV", 11.99, R.drawable.icon_apple_tv),
    Subscription("Netflix", 9.99, R.drawable.icon_netflix, true),
    Subscription("Prime Video", 7.99, R.drawable.icon_prime_video, true),
    Subscription("Jio Hotstar", 6.99, R.drawable.icon_jio_hotstar, true),
    Subscription("Sony LIVE", 5.99, R.drawable.icon_sony),
    Subscription("ZEE Premium", 4.99, R.drawable.icon_zee)
)
