package com.nbs.composestatedemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var count by mutableStateOf(1)

    fun updateCount() {
        count+=count
    }

}