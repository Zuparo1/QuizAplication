package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.quizDataClass.TextInputData
import com.example.quizaplication.model.quizDataClass.TrueOrFalseData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class TextInputViewModel : ViewModel() {
    private val _textInput = MutableLiveData<List<TextInputData>>()
    val textInput: LiveData<List<TextInputData>> get() = _textInput

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val quizRef = db.collection(collectionPath).document(documentPath)


        quizRef.get().addOnSuccessListener { document ->
            val questionList = mutableListOf<TextInputData>()
            val questionData = document.data?.get("questions") as? Map<String, Map<String, Any>>

            questionData?.forEach { (_,questionMap) ->
                val question = questionMap as? Map<String, Any>
                val questionText = question?.get("questionText") as? String ?: ""
                val answer = question?.get("answer") as? String ?: ""
                questionList.add(TextInputData(answer, questionText))
                println(questionList)
                println(questionList.size)
            }
            _textInput.postValue(questionList)
        }
    }
}