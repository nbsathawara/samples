package com.anushka.viewmodelscopedemo

import kotlinx.coroutines.delay

class UserRepository {

     suspend fun getUsers(): List<User> {
        delay(8000)
        return listOf(
            User(1, "Nikhil"), User(2, "Yash"), User(3, "Jenil"), User(4, "Hemangi")
        )
    }

}