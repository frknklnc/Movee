package com.example.movee.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class AuthRepository {

    fun hasUser(): Boolean = Firebase.auth.currentUser != null

    suspend fun createUser(email: String, password: String, onComplete: (Boolean) -> Unit) =
        withContext(Dispatchers.IO) {
            Firebase.auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() {
                    if (it.isSuccessful) {
                        onComplete.invoke(true)
                    } else {
                        onComplete.invoke(false)
                    }
                }.await()
        }

    suspend fun login(email: String, password: String, onComplete: (Boolean) -> Unit) =
        withContext(Dispatchers.IO) {
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() {
                    if (it.isSuccessful) {
                        onComplete.invoke(true)
                    } else {
                        onComplete.invoke(false)
                    }
                }.await()
        }

}