package com.anushka.livedatademo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModel(private val startingTotal: Int) : ViewModel() {

    private var total = MutableLiveData<Int>()
    val totalData: LiveData<Int>
        get() = total

    init {
        total.value = startingTotal
    }

    fun add(input: Int?) {
        if (input != null)
            total.value = (total.value)?.plus(input)

    }

    class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
                return MainActivityViewModel(startingTotal) as T
            return super.create(modelClass)
        }
    }
}

