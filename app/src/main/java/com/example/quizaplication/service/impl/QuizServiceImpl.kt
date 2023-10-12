package com.example.quizaplication.service.impl

import com.example.quizaplication.model.Quiz
import com.example.quizaplication.service.QuizService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class QuizServiceImpl @Inject constructor(private val firestore: FirebaseFirestore) : QuizService {

    override suspend fun getQuiz(name: String): Quiz? =
        firestore.collection("Quizzes").document(name).get().await().toObject()

}