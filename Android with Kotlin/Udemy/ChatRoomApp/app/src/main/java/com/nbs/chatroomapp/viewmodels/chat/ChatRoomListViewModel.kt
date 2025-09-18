package com.nbs.chatroomapp.viewmodels.chat

import androidx.lifecycle.viewModelScope
import com.nbs.chatroomapp.data.models.ChatRoom
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.repository.AccountRepository
import com.nbs.chatroomapp.repository.ChatRoomRepository
import com.nbs.chatroomapp.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatRoomListViewModel @Inject constructor(
    accountRepository: AccountRepository,
    private val chatRoomRepository: ChatRoomRepository
) : BaseViewModel(accountRepository) {

    private val _chatRooms =
        MutableStateFlow(emptyList<ChatRoom>())
    val chatRooms = _chatRooms.asStateFlow()

    init {
        fetchChatRooms()
    }

    fun fetchChatRooms() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val result = chatRoomRepository.getChatRooms()
                when (result) {
                    is HttpResult.Success -> {
                        _chatRooms.value = result.data
                    }

                    is HttpResult.Error -> {
                        setMsg(result.exception.localizedMessage ?: "Something went wrong")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                setLoading(false)
            }
        }
    }

    fun createChatRoom(chatRoom: ChatRoom) {
        viewModelScope.launch {
            try {
                setLoading(true)
                val result = chatRoomRepository.createChatRoom(chatRoom)
                when (result) {
                    is HttpResult.Success -> {
                        _chatRooms.value = _chatRooms.value + result.data
                        setMsg("Chat room created successfully")
                    }

                    is HttpResult.Error -> {
                        setMsg(result.exception.localizedMessage ?: "Something went wrong")
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                setLoading(false)
            }
        }
    }
}