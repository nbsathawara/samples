package com.nbs.mywishlistapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

object Constants {
    const val AppTag = "WishList Debug"

    //Database
    const val TABLE_WISH = "table_wish"

    //icons
    val backIcon = Icons.AutoMirrored.Default.ArrowBack
}

enum class Screens {
    HomeScreen, AddWishScreen
}