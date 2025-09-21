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
import kotlinx.coroutines.flow.update
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
                    id = "message_" + System.currentTimeMillis(),
                    text = message,
                    sender = currentUser.value!!.firstName,
                    timestamp = System.currentTimeMillis()
                )
                val result = messageRepository.sendMessage(_roomId.value, msg)
                when (result) {
                    is HttpResult.Success -> {
                        _allMessages.value = _allMessages.value + result.data
                        addMessageToUI(result.data)
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

    fun updateMessage(message: Message) {
        viewModelScope.launch {
            try {
                val result = messageRepository.updateMessage(_roomId.value, message)
                when (result) {
                    is HttpResult.Success -> {
                        _allMessages.value = _allMessages.value.map {
                            if (it.id == result.data.id)
                                result.data
                            else
                                it
                        }
                        _messageList.update {
                            it.map {
                                if (it is Message && it.id == result.data.id)
                                    result.data
                                else
                                    it
                            }
                        }
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

    fun deleteMessages(messages: List<Message>) {
        viewModelScope.launch {
            try {
                val result = messageRepository.deleteMessages(_roomId.value, messages)
                when (result) {
                    is HttpResult.Success -> {
                        _allMessages.value = _allMessages.value - messages
                        _messageList.update {
                            it - messages
                        }
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
                    addMessagesToUI(messages = it)
                }
            } catch (e: Exception) {
                setMessage(e.localizedMessage ?: "Unknown Error")
            } finally {
                setLoading(false)
            }
        }
    }

    fun addMessageToUI(message: Message) {
        _messageList.update {
            it + message
        }
    }

    fun addMessagesToUI(messages: List<Message>) {
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