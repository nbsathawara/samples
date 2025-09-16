package com.nbs.chatroomapp.viewmodels.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nbs.chatroomapp.Graph
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private var userRepository: UserRepository = UserRepository(
        auth = FirebaseAuth.getInstance(),
        fireStore = Graph.fireStoreInstance()
    )

    private val _authResult = MutableStateFlow<HttpResult<*>>(HttpResult.Success(false))
    val authResult: StateFlow<HttpResult<*>> = _authResult.asStateFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                val result = userRepository.signIn(email, password)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            }
        }
    }

    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch {
            try {
                val result = userRepository.signUp(email, password, firstName, lastName)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            }
        }
    }
}