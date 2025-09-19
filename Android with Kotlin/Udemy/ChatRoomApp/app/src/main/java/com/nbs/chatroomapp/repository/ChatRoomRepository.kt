package com.nbs.chatroomapp.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.nbs.chatroomapp.data.Constants
import com.nbs.chatroomapp.data.models.ChatRoom
import com.nbs.chatroomapp.data.models.HttpResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


interface ChatRoomRepository {
    suspend fun getChatRooms(): HttpResult<List<ChatRoom>>
    suspend fun createChatRoom(chatRoom: ChatRoom): HttpResult<ChatRoom>
}
class ChatRoomRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : ChatRoomRepository {

    override suspend fun createChatRoom(chatRoom: ChatRoom)
            : HttpResult<ChatRoom> =
        try {
            fireStore.collection(Constants.chatRoomsCollection)
                .document(chatRoom.id).set(chatRoom).await()
            HttpResult.Success(chatRoom)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }

    override suspend fun getChatRooms(): HttpResult<List<ChatRoom>> =
        try {
            val snapshot = fireStore.collection(Constants.chatRoomsCollection)
                .get().await()
            val chatRooms = snapshot.toObjects(ChatRoom::class.java)
            HttpResult.Success(chatRooms)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
}
