package com.example.quizaplication.service

import com.example.quizaplication.model.UserData
import com.example.quizaplication.model.leaderboardData.UserScore
import com.example.quizaplication.model.userDatas.Score

interface UserDataService {
    suspend fun createUserData(id: String, userData: UserData)
    suspend fun getName(id: String): String?
    suspend fun changeName(id: String, name: String, onResult: (Throwable?) -> Unit)
    suspend fun updateScore(id: String, quizType: String, subject: String, points: Int)
    suspend fun getUserScores(id: String, type: String): Score
    suspend fun getScores(type: String, subject: String): MutableList<UserScore>
}