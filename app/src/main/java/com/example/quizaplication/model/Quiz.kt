package com.example.quizaplication.model

import com.google.firebase.firestore.DocumentId

data class Quiz(
    @DocumentId val uid: String = "",
    val name: String = "",
    val questions: List<Question>
)