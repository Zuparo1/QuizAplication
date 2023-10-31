package com.example.quizaplication.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.Quiz
import com.example.quizaplication.model.QuizCategory
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizViewModel : ViewModel() {

  private val db = Firebase.firestore
  private val _quiz = MutableLiveData<Quiz>()
  val quiz: LiveData<Quiz> get() = _quiz

  fun fetchHistoryData() {
    db.collection("MultipleChoiceQuiz").document("History").get()
      .addOnSuccessListener { document ->
        if (document != null) {
          val quiz = document.toObject(Quiz::class.java)
          _quiz.value = quiz
        } else {
          // Handle the error or set some default value
        }
      }
      .addOnFailureListener { exception ->
        // Handle the error or set some default value
      }


  }
  /*init {
    fetchHistoryData()
  }*/
}