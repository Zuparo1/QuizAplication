package com.example.quizaplication.service.impl

import android.util.Log
import com.example.quizaplication.model.UserData
import com.example.quizaplication.model.userDatas.MultipleChoice
import com.example.quizaplication.service.UserDataService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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

    //Non working, doesn't return list before the check is complete in signup
    override suspend fun getAllNames(): List<String> {
        val userNames = mutableListOf<String>()
        val userData = firestore.collection("userData")

        userData.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                val value = document.getString("userName")

                if (value != null) {
                    userNames.add(value)
                }
            }

        }.await()

        return userNames
    }

    override suspend fun updateScore(id: String, quizType: String, subject: String, points: Int) {
        val updates = hashMapOf<String, Any>(
            "${quizType}.${subject}" to points
        )
        firestore.collection("userData").document(id).update(updates)
    }

    override suspend fun getScores(id: String, quizType: String): UserData {
        val userData = UserData()
        val userDataRef = firestore.collection("userData").document(id)

        userDataRef.get().addOnSuccessListener { document ->
            userData.multipleChoice.dataSecurity = document["multipleChoice.dataSecurity"] as Long
            userData.multipleChoice.biology = document["multipleChoice.biology"] as Long
            userData.multipleChoice.chemistry = document["multipleChoice.chemistry"] as Long
            userData.multipleChoice.math = document["multipleChoice.math"] as Long
            userData.multipleChoice.english = document["multipleChoice.english"] as Long
            userData.multipleChoice.history = document["multipleChoice.history"] as Long
            userData.multipleChoice.mix = document["multipleChoice.mix"] as Long
            userData.multipleChoice.norwegian = document["multipleChoice.norwegian"] as Long
            userData.multipleChoice.programing = document["multipleChoice.programing"] as Long
            userData.multipleChoice.science = document["multipleChoice.science"] as Long
        }.await()
        return userData
    }

}