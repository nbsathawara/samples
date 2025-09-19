package com.nbs.chatroomapp.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nbs.chatroomapp.data.Constants
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

interface AccountRepository {
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): HttpResult<Boolean>

    suspend fun signIn(email: String, password: String): HttpResult<Boolean>

    suspend fun getCurrentUser(): HttpResult<User>
}

class AccountRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AccountRepository {

    override suspend fun getCurrentUser(): HttpResult<User> =
        try {
            val currentUser = auth.currentUser
            if (currentUser == null)
                HttpResult.Error(Exception("User not logged in"))
            val email = currentUser!!.email!!
            val user = fireStore.collection(Constants.usersCollection)
                .document(email).get().await()
                .toObject(User::class.java)
            HttpResult.Success(user!!)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }

    override suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): HttpResult<Boolean> =
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(
                email = email,
                firstName = firstName,
                lastName = lastName
            )
            saveUserToFiresStore(user)
            HttpResult.Success(true)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }


    override suspend fun signIn(email: String, password: String): HttpResult<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            HttpResult.Success(true)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }

    suspend fun saveUserToFiresStore(user: User) {
        fireStore.collection(Constants.usersCollection).document(user.email).set(user).await()
    }
}