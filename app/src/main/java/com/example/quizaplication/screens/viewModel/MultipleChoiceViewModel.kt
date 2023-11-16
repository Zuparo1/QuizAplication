package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.quizDataClass.MultipleChoiceData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MultipleChoiceViewModel : ViewModel() {
    private val _quizQuestions = MutableLiveData<List<MultipleChoiceData>>()
    val quizQuestions: LiveData<List<MultipleChoiceData>> get() = _quizQuestions

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val historyRef = db.collection(collectionPath).document(documentPath)

      historyRef.get().addOnSuccessListener { document ->
            val questionData = document["questions"] as? Map<String, Any>
            val questionList = mutableListOf<MultipleChoiceData>()

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
                        questionList.add(MultipleChoiceData(prompt, optionsArray, correct))
                    }
                    _quizQuestions.postValue(questionList)
        }
    }
}