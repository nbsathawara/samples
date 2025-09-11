package com.nbs.subsriptionapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

object Constants {
    const val AppTag = "SubscriptionApp Debug"

    //icons
    val backIcon = Icons.AutoMirrored.Default.ArrowBack
}

enum class Screens {
    MyAccountScreen, SubscriptionsScreen,AddAccountScreen
}