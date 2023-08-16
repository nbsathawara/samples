package com.anushka.viewmodelscopedemo

import androidx.lifecycle.*
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {

    private val userRepository = UserRepository()
    //var liveUsers: MutableLiveData<List<User>> = MutableLiveData()
//    fun getUserData() {
//        viewModelScope.launch {
//            var users: List<User>
//            withContext(Dispatchers.IO) {
//                users = userRepository.getUsers()
//            }
//            liveUsers.value = users
//        }
//    }

    var users = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()
        emit(result)
    }
}