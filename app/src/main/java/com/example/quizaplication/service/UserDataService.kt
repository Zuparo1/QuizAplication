package com.example.quizaplication.service

import com.example.quizaplication.model.UserData

interface UserDataService {
    suspend fun createUserData(id: String, userData: UserData)
    suspend fun getName(id: String): String?
    suspend fun changeName(id: String, name: String, onResult: (Throwable?) -> Unit)
    suspend fun updateScore(id: String, quizType: String, subject: String, points: Int)
    suspend fun getScores(id: String, quizType: String): UserData
}