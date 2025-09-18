package com.nbs.chatroomapp.viewmodels.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.network.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _authResult = MutableStateFlow<HttpResult<*>>(HttpResult.Success(false))
    val authResult: StateFlow<HttpResult<*>> = _authResult.asStateFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = accountService.signIn(email, password)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            } finally {
                _isLoading.value = false
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
                _isLoading.value = true
                val result = accountService.signUp(email, password, firstName, lastName)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}