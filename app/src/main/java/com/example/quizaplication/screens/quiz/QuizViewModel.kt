package com.example.quizaplication.screens.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.QuizQuestion
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizViewModel : ViewModel() {
    private val _quizQuestions = MutableLiveData<List<QuizQuestion>>()
    val quizQuestions: LiveData<List<QuizQuestion>> get() = _quizQuestions

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val historyRef = db.collection(collectionPath).document(documentPath)

      historyRef.get().addOnSuccessListener { document ->
            val questionData = document["questions"] as? Map<String, Any>
            val questionList = mutableListOf<QuizQuestion>()

            questionData?.forEach { (_, value) ->
                val questionMap = value as? Map<String, Any>
                val prompt = questionMap?.keys?.firstOrNull() { it != "correct" }?: ""
                val options = mutableListOf<String>()
                (questionMap?.get(prompt) as? List<*>)?.forEach { item ->
                    item?.let {
                        options.add(it.toString())
                    }
                }
                        val optionsArray = options.toTypedArray()
                        val correct = questionMap?.get("correct") as? String ?: ""
                        questionList.add(QuizQuestion(prompt, optionsArray, correct))
                    }
                    _quizQuestions.postValue(questionList)
        }
    }
}