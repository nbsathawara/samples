package com.nbs.chatroomapp.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nbs.chatroomapp.data.models.HttpResult
import com.nbs.chatroomapp.data.models.User
import com.nbs.subsriptionapp.data.HttpStatus
import kotlinx.coroutines.tasks.await
import java.lang.Exception


class UserRepository(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) {

    suspend fun signUp(email: String, password: String, firstName: String, lastName: String)
            : HttpResult<Boolean> =
        try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
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

    suspend fun saveUserToFiresStore(user: User) {
        fireStore.collection("users").document(user.email).set(user).await()
    }

    suspend fun signIn(email: String, password: String): HttpResult<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            HttpResult.Success(true)
        } catch (e: Exception) {
            HttpResult.Error(e)
        }
}