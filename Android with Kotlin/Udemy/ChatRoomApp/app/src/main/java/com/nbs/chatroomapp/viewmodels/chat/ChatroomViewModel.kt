package com.nbs.chatroomapp.viewmodels.chat

import androidx.lifecycle.viewModelScope
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.Message
import com.nbs.chatroomapp.data.toChatDate
import com.nbs.chatroomapp.repository.AccountRepository
import com.nbs.chatroomapp.repository.MessageRepository
import com.nbs.chatroomapp.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatroomViewModel @Inject constructor(
    accountRepository: AccountRepository,
    private val messageRepository: MessageRepository
) : BaseViewModel(
    accountRepository
) {
    private val _allMessages = MutableStateFlow(emptyList<Message>())

    private val _messageList = MutableStateFlow(emptyList<Any>())
    val messageList = _messageList.asStateFlow()

    private val _roomId = MutableStateFlow("")
    fun setRoomId(roomId: String) {
        _roomId.value = roomId
        fetchMessages()
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            try {
                val msg = Message(
                    text = message,
                    sender = currentUser.value!!.firstName,
                    timestamp = System.currentTimeMillis()
                )
                val result = messageRepository.sendMessage(_roomId.value, msg)
                when (result) {
                    is HttpResult.Success -> {
                        _allMessages.value = _allMessages.value + result.data
                        updateMessage(result.data)
                    }

                    is HttpResult.Error -> {
                        setMessage(result.exception.localizedMessage ?: "Unknown Error")
                    }
                }
            } catch (e: Exception) {
                setMessage(e.localizedMessage ?: "Unknown Error")
            }
        }
    }

    fun fetchMessages() {
        viewModelScope.launch {
            try {
                setLoading(true)
                val result = messageRepository.getMessages(_roomId.value)
                result.collect {
                    _allMessages.value = it
                    updateMessages(messages = it)
                }
            } catch (e: Exception) {
                setMessage(e.localizedMessage ?: "Unknown Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun updateMessage(message: Message) {
        _messageList.value = _messageList.value + message
    }

    fun updateMessages(messages: List<Message>) {
        val tmpMap = mutableMapOf<String, List<Message>>()
        messages.forEach { message ->
            val key = message.timestamp.toChatDate()
            val value = tmpMap[key]
            if (value == null)
                tmpMap[key] = listOf(message)
            else
                tmpMap[key] = value + message
        }
        _messageList.value = tmpMap.flatMap { it ->
            listOf(it.key) + it.value
        }
    }

//    fun updateMessageMap(messages: List<Message>) {
//        val tmpMap = mutableMapOf<String, List<Message>>()
//        messages.forEach { message ->
//            val key = message.timestamp.toChatDate()
//            val value = tmpMap[key]
//            if (value == null)
//                tmpMap[key] = listOf(message)
//            else
//                tmpMap[key] = value + message
//        }
//        _messageMap.value = tmpMap
//    }
}