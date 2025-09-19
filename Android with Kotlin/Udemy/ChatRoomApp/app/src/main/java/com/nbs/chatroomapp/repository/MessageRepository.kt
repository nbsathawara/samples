package com.nbs.chatroomapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.nbs.chatroomapp.data.Constants
import com.nbs.chatroomapp.data.models.ChatRoom
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.Message
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface MessageRepository {
    suspend fun sendMessage(roomId: String, message: Message): HttpResult<Message>
    suspend fun getMessages(chatRoomId: String): HttpResult<List<Message>>
}

class MessageRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : MessageRepository {

    override suspend fun sendMessage(roomId: String, message: Message)
            : HttpResult<Message> = try {
        fireStore.collection(Constants.chatRoomsCollection)
            .document(roomId)
            .collection(Constants.messagesCollection)
            .add(message).await()
        HttpResult.Success(message)
    } catch (e: Exception) {
        HttpResult.Error(e)
    }

    override suspend fun getMessages(chatRoomId: String)
            : HttpResult<List<Message>> = try {
        val snapshot = fireStore.collection(Constants.chatRoomsCollection)
            .document(chatRoomId)
            .collection(Constants.messagesCollection)
            .orderBy(Constants.messagesTimestamp)
            .get().await()
        val messages = snapshot.toObjects(Message::class.java)
        HttpResult.Success(messages)
    } catch (e: Exception) {
        HttpResult.Error(e)
    }
}