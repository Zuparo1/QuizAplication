package com.example.quizaplication.service.impl

import android.util.Log
import com.example.quizaplication.common.ext.lowerCaseFirstChar
import com.example.quizaplication.model.UserData
import com.example.quizaplication.model.leaderboardData.UserScore
import com.example.quizaplication.model.userDatas.Score
import com.example.quizaplication.service.UserDataService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserDataServiceImpl @Inject constructor(private val firestore: FirebaseFirestore) : UserDataService {
    override suspend fun createUserData(id: String, userData: UserData) {
        firestore.collection("userData").document(id).set(userData)
    }

    override suspend fun getName(id: String): String? {
        var userName: String? = ""
        val userDataRef = firestore.collection("userData").document(id)

        userDataRef.get().addOnSuccessListener { document ->
            userName = document["userName"] as? String
        }.await()
        return userName
    }

    override suspend fun changeName(
        id: String,
        name: String,
        onResult: (Throwable?) -> Unit
        ) {
        val updates = hashMapOf<String, Any>(
            "userName" to name
        )
        firestore.collection("userData").document(id).update(updates).addOnCompleteListener { onResult(it.exception) }.await()
    }

    override suspend fun updateScore(id: String, quizType: String, subject: String, points: Int) {
        var existingPoints = 0L;
        val userDataRef = firestore.collection("userData").document(id)
        userDataRef.get().addOnSuccessListener { document ->
            existingPoints = document["${quizType.lowerCaseFirstChar()}.${subject.lowerCaseFirstChar()}"] as Long
        }.await()
        Log.d("Points", "${existingPoints.toInt()}-${points}")
        if (existingPoints.toInt() >= points) {
            return
        }
        val updates = hashMapOf<String, Any>(
            "${quizType.lowerCaseFirstChar()}.${subject.lowerCaseFirstChar()}" to points
        )
        firestore.collection("userData").document(id).update(updates)
    }

    override suspend fun getUserScores(id: String, type: String): Score {
        val score = Score()
        val userDataRef = firestore.collection("userData").document(id)

        userDataRef.get().addOnSuccessListener { document ->
            score.math = document["${type}.math"] as Long
            score.history = document["${type}.history"] as Long
            score.programing = document["${type}.programing"] as Long
            score.science = document["${type}.science"] as Long
        }.await()
        return score
    }

    override suspend fun getScores(type: String, subject: String): MutableList<UserScore> {
        val userScores = mutableListOf<UserScore>()

        val userDataRef = firestore.collection("userData").get().await()
        for (document in userDataRef) {
            val userScore = UserScore()

            userScore.username = document["userName"] as String? ?: ""
            userScore.score = document["${type}.${subject}"] as Long? ?: 0L

            userScores.add(userScore)
        }

        userScores.sortByDescending { it.score }

        return userScores
    }
}