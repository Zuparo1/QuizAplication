package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaplication.model.quizDataClass.TrueOrFalseData
import com.example.quizaplication.service.UserDataService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrueOrFalseViewModel @Inject constructor(
    private val userDataService: UserDataService,
) : ViewModel() {
    private val _trueOrFalse = MutableLiveData<List<TrueOrFalseData>>()
    val trueOrFalse: LiveData<List<TrueOrFalseData>> get() = _trueOrFalse

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val quizRef = db.collection(collectionPath).document(documentPath)


        quizRef.get().addOnSuccessListener { document ->
            val questionList = mutableListOf<TrueOrFalseData>()
            val questionData = document.data?.get("questions") as? Map<String, Map<String, Any>>

            questionData?.forEach { (_,questionMap) ->
                val question = questionMap as? Map<String, Any>
                val questionText = question?.get("questionText") as? String ?: ""
                val isTrue = question?.get("isTrue") as? Boolean ?: false
                questionList.add(TrueOrFalseData(isTrue, questionText))
                println(questionList)
                println(questionList.size)
            }
            _trueOrFalse.postValue(questionList)
        }
    }

    fun savePointsToDatabase(id: String, subject: String, difficulty: String, points: Int) {
        viewModelScope.launch {
            try {
                userDataService.updateScore(id, "TrueOrFalse", subject, difficulty, points)
            } catch (_: Exception) {  }
        }
    }
}