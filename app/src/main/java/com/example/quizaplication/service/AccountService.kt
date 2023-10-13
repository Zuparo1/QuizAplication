package com.example.quizaplication.service

import com.example.quizaplication.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUser: Flow<User>

    suspend fun createAnonymousAccount()
    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun createAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun signOut()
}