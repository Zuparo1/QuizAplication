package com.example.quizaplication.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState

@Composable
fun MultipleChoice(navController: NavController, viewModel: QuizViewModel) {
    val quiz by viewModel.quiz.collectAsState(initial = null)
}

@Preview
@Composable
fun multiPreview() {
    //MultipleChoice()
}