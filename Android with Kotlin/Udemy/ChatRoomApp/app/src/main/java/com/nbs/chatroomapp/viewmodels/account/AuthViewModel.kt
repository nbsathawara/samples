package com.nbs.chatroomapp.viewmodels.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.repository.AccountRepository
import com.nbs.chatroomapp.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : BaseViewModel(accountRepository) {

    private val _authResult = MutableStateFlow<HttpResult<*>>(HttpResult.Success(false))
    val authResult: StateFlow<HttpResult<*>> = _authResult.asStateFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            try {
                setLoading(true)
                val result = accountRepository.signIn(email, password)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            } finally {
                setLoading(false)
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
                setLoading(true)
                val result = accountRepository.signUp(email, password, firstName, lastName)
                _authResult.value = result
            } catch (e: Exception) {
                _authResult.value = HttpResult.Error(e)
            } finally {
                setLoading(false)
            }
        }
    }
}