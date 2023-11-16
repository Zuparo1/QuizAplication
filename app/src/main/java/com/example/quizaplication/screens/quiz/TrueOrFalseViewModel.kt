package com.example.quizaplication.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.QuizQuestion
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class TrueOrFalseViewModel : ViewModel() {
    private val _quizQuestions = MutableLiveData<List<QuizQuestion>>()
    val quizQuestions: LiveData<List<QuizQuestion>> get() = _quizQuestions

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val quizRef = db.collection(collectionPath).document(documentPath)

        quizRef.get().addOnSuccessListener { document ->
            val questionList = mutableListOf<QuizQuestion>()
            val questionData = document.data?.get("questions") as? List<Map<String, Any>>

            questionData?.forEach { questionMap ->
                val questionText = questionMap["questionText"] as? String ?: ""
                val isTrue = questionMap["isTrue"] as? Boolean ?: false
                val options = arrayOf("True", "False")
                val correctAnswer = if (isTrue) "True" else "False"
                questionList.add(QuizQuestion(questionText, options, correctAnswer))
            }
            _quizQuestions.postValue(questionList)
        }
    }
}