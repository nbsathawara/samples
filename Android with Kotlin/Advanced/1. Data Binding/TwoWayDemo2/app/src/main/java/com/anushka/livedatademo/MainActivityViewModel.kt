package com.anushka.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel(private val startingTotal: Int) : ViewModel() {

    var input = MutableLiveData<String>()
    var total = MutableLiveData<String>()

    init {
        input.value = "0"
        total.value = startingTotal.toString()
    }

    fun add() {
        if (input != null)
            total.value = (total.value)?.toInt()?.plus(input.value?.toInt() ?: 0).toString()

    }

    class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
                return MainActivityViewModel(startingTotal) as T
            return super.create(modelClass)
        }
    }
}

