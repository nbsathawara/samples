package com.example.twowaydatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel : ViewModel() {
     var userName = MutableLiveData<String>()
//    val userNameData: LiveData<String>
//        get() = userName

    init {
        userName.value = "Nikhil Sathawara"
    }
}