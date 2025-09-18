package com.nbs.chatroomapp.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.User
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AccountService {

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
        fireStore.collection("users").document(user.email).set(user).await()
    }
}


interface AccountService {
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): HttpResult<Boolean>

    suspend fun signIn(email: String, password: String): HttpResult<Boolean>
}