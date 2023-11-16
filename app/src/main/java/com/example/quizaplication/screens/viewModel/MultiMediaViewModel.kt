package com.example.quizaplication.screens.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizaplication.model.quizDataClass.MultiMediaData
import com.example.quizaplication.model.quizDataClass.TextInputData
import com.example.quizaplication.model.quizDataClass.TrueOrFalseData
import com.example.quizaplication.navigation.Screen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
class MultiMediaViewModel : ViewModel() {
    private val _multiMedia = MutableLiveData<List<MultiMediaData>>()
    val multiMedia: LiveData<List<MultiMediaData>> get() = _multiMedia

    fun fetchQuizData(collectionPath : String, documentPath : String) {
        val db = Firebase.firestore
        val quizRef = db.collection(collectionPath).document(documentPath)


        quizRef.get().addOnSuccessListener { document ->
            val questionList = mutableListOf<MultiMediaData>()
            val questionData = document.data?.get("questions") as? Map<String, Map<String, Any>>

            questionData?.forEach { (_,questionMap) ->
                val questionText = questionMap["questionText"] as? String ?: ""
                val options = questionMap["options"] as? List<String> ?: emptyList()
                val correctAnswer = questionMap["correctAnswer"] as? String ?: ""
                val multimediaUrl = questionMap["multimediaUrl"] as? String ?: ""
                val multimediaType = questionMap["multimediaType"] as? String ?: ""

                questionList.add(MultiMediaData(questionText, options, correctAnswer,multimediaUrl, multimediaType))
                println(questionList)
                println(questionList.size)
            }
            _multiMedia.postValue(questionList)
        }
    }
}