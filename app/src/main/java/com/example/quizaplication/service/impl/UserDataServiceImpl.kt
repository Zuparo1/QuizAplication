package com.example.quizaplication.service.impl

import android.util.Log
import com.example.quizaplication.common.ext.lowerCaseFirstChar
import com.example.quizaplication.model.UserData
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

    override suspend fun getUserScores(id: String): UserData {
        val userData = UserData()
        val userDataRef = firestore.collection("userData").document(id)

        userDataRef.get().addOnSuccessListener { document ->
            userData.multipleChoiceQuiz.math = document["multipleChoiceQuiz.math"] as Long
            userData.multipleChoiceQuiz.history = document["multipleChoiceQuiz.history"] as Long
            userData.multipleChoiceQuiz.programing = document["multipleChoiceQuiz.programing"] as Long
            userData.multipleChoiceQuiz.science = document["multipleChoiceQuiz.science"] as Long

            userData.trueOrFalse.math = document["trueOrFalse.math"] as Long
            userData.trueOrFalse.history = document["trueOrFalse.history"] as Long
            userData.trueOrFalse.programing = document["trueOrFalse.programing"] as Long
            userData.trueOrFalse.science = document["trueOrFalse.science"] as Long

            userData.textInput.math = document["textInput.math"] as Long
            userData.textInput.history = document["textInput.history"] as Long
            userData.textInput.programing = document["textInput.programing"] as Long
            userData.textInput.science = document["textInput.science"] as Long

            userData.multiMedia.math = document["multiMedia.math"] as Long
            userData.multiMedia.history = document["multiMedia.history"] as Long
            userData.multiMedia.programing = document["multiMedia.programing"] as Long
            userData.multiMedia.science = document["multiMedia.science"] as Long
        }.await()
        return userData
    }

    override suspend fun getScores(): MutableList<UserData> {
        val userDataList = mutableListOf<UserData>()

        val userDataRef = firestore.collection("userData").get().await()
        for (document in userDataRef) {
            val userData = UserData()
            userData.multipleChoiceQuiz.math = document["multipleChoiceQuiz.math"] as Long
            userData.multipleChoiceQuiz.history = document["multipleChoiceQuiz.history"] as Long
            userData.multipleChoiceQuiz.programing = document["multipleChoiceQuiz.programing"] as Long
            userData.multipleChoiceQuiz.science = document["multipleChoiceQuiz.science"] as Long

            userData.trueOrFalse.math = document["trueOrFalse.math"] as Long
            userData.trueOrFalse.history = document["trueOrFalse.history"] as Long
            userData.trueOrFalse.programing = document["trueOrFalse.programing"] as Long
            userData.trueOrFalse.science = document["trueOrFalse.science"] as Long

            userData.textInput.math = document["textInput.math"] as Long
            userData.textInput.history = document["textInput.history"] as Long
            userData.textInput.programing = document["textInput.programing"] as Long
            userData.textInput.science = document["textInput.science"] as Long

            userData.multiMedia.math = document["multiMedia.math"] as Long
            userData.multiMedia.history = document["multiMedia.history"] as Long
            userData.multiMedia.programing = document["multiMedia.programing"] as Long
            userData.multiMedia.science = document["multiMedia.science"] as Long

            userDataList.add(userData)
        }

        return userDataList
    }
}