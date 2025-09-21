package com.nbs.chatroomapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.nbs.chatroomapp.data.Constants
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.Message
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface MessageRepository {
    suspend fun sendMessage(roomId: String, message: Message): HttpResult<Message>
    suspend fun getMessages(chatRoomId: String): Flow<List<Message>>
    suspend fun deleteMessages(roomId: String, messages: List<Message>): HttpResult<Boolean>

    suspend fun updateMessage(roomId: String, message: Message): HttpResult<Message>
}

class MessageRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : MessageRepository {

    override suspend fun sendMessage(roomId: String, message: Message)
            : HttpResult<Message> = try {
        upsertMessage(roomId, message)
        HttpResult.Success(message)
    } catch (e: Exception) {
        HttpResult.Error(e)
    }

    override suspend fun updateMessage(
        roomId: String,
        message: Message
    ): HttpResult<Message> = try {
        upsertMessage(roomId, message)
        HttpResult.Success(message)
    } catch (e: Exception) {
        HttpResult.Error(e)
    }

    suspend fun upsertMessage(roomId: String, message: Message) {
        fireStore.collection(Constants.chatRoomsCollection)
            .document(roomId)
            .collection(Constants.messagesCollection)
            .document(message.id)
            .set(message).await()
    }

    override suspend fun deleteMessages(roomId: String, messages: List<Message>)
            : HttpResult<Boolean> = try {
        messages.forEach {
            fireStore.collection(Constants.chatRoomsCollection)
                .document(roomId)
                .collection(Constants.messagesCollection)
                .document(it.id)
                .delete()
        }
        HttpResult.Success(true)
    } catch (e: Exception) {
        HttpResult.Error(e)
    }

    override suspend fun getMessages(chatRoomId: String)
            : Flow<List<Message>> = callbackFlow {

        val messages = fireStore.collection(Constants.chatRoomsCollection)
            .document(chatRoomId)
            .collection(Constants.messagesCollection)
            .orderBy("timestamp")

        val subscription = messages.addSnapshotListener { snapshot, error ->
            snapshot?.let {
                val messages = it.toObjects(Message::class.java)
                trySend(messages).isSuccess
            }
        }
        awaitClose {
            subscription.remove()
        }
    }
}