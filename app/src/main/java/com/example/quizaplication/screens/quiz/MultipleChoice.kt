package com.example.quizaplication.screens.quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MultipleChoice(navController: NavController) {
    val viewModel: QuizViewModel = viewModel()
    val quizQuestions by viewModel.quizQuestions.observeAsState(initial = emptyList())

    LaunchedEffect(Unit){
        viewModel.fetchHistoryData()
    }


        Column(modifier = Modifier.padding(16.dp)) {
        quizQuestions.forEachIndexed { index, question ->
            Text(text = "${index + 1}. OPTIONS: ${question.options.joinToString(", ")}")
            Text(text = "${index + 1}. PROMPT: ${question.prompt}")
            Text(text = "${index + 1}. CORRECT: ${question.correct}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}

@Preview
@Composable
fun MultiPreview() {
    lateinit var navController: NavHostController
    navController = rememberNavController()
    MultipleChoice(navController)
}