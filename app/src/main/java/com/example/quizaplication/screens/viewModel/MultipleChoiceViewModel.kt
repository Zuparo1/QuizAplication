package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizaplication.model.quizDataClass.MultipleChoiceData
import com.example.quizaplication.service.UserDataService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultipleChoiceViewModel @Inject constructor(
    private val userDataService: UserDataService,
) : ViewModel() {
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

    fun savePointsToDatabase(id: String, subject: String, points: Int) {
        viewModelScope.launch {
            try {
                userDataService.updateScore(id, "MultipleChoiceQuiz", subject, points)
            } catch (_: Exception) {  }
        }
    }
}