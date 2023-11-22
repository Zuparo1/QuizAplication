package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.model.quizDataClass.TextInputData
import com.example.quizaplication.model.quizDataClass.TrueOrFalseData
import com.example.quizaplication.service.UserDataService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextInputViewModel @Inject constructor(
    private val userDataService: UserDataService,
) : ViewModel() {
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

    fun savePointsToDatabase(id: String, subject: String, difficulty: String, points: Int) {
        viewModelScope.launch {
            try {
                userDataService.updateScore(id, "TextInput", subject, difficulty, points)
            } catch (_: Exception) {  }
        }
    }
}