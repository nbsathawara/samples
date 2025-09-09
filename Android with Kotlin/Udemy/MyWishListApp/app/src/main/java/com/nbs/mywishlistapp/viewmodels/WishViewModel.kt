package com.nbs.mywishlistapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class WishViewModel : ViewModel() {
    var wishTitle by mutableStateOf("")
    var wishDesc by mutableStateOf("")

    fun editWishTitle(title: String) {
        wishTitle = title
    }

    fun editWishDesc(desc: String) {
        wishDesc = desc
    }
}