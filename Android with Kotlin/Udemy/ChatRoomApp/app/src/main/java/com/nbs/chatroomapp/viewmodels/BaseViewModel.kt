package com.nbs.chatroomapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.User
import com.nbs.chatroomapp.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()
    fun setLoading(value: Boolean = false) {
        _isLoading.value = value
    }

    private val _msg = MutableStateFlow("")
    var msg = _msg.asStateFlow()
    fun setMsg(value: String = "") {
        _msg.value = value
    }

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        fetchUserDetails("nbsathawara@gmail.com")
    }

    fun fetchUserDetails(email: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = accountRepository.getUserDetails(email)
                when (result) {
                    is HttpResult.Success -> {
                        _currentUser.value = result.data
                    }

                    is HttpResult.Error -> {
                        _msg.value = result.exception.localizedMessage ?: "Something went wrong"
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}