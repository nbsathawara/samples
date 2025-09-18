package com.nbs.chatroomapp.data.models

sealed class HttpResult<out T> {
    data class Success<out T>(val data: T) : HttpResult<T>()
    data class Error(val exception: Exception) : HttpResult<Nothing>()
}