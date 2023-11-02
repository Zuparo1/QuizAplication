package com.example.quizaplication.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun MultipleChoice(navController: NavController, documentPath : String) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var buttonBackgroundColor by remember { mutableStateOf(Color.Transparent) }

    val viewModel: QuizViewModel = viewModel()
    val quizQuestions by viewModel.quizQuestions.observeAsState(initial = emptyList())
    LaunchedEffect(Unit){
        viewModel.fetchQuizData("MultipleChoiceQuiz",documentPath)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
    ){
        TopNavBar(navController)
        Text(
            text = "$documentPath: Multiple Choice",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(16.dp)
        )
        if (currentQuestionIndex < quizQuestions.size){
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ){
                val question = quizQuestions[currentQuestionIndex]
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ){
                    Text(
                        text = question.prompt,
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                question.options.forEach { option ->
                    Button(
                        onClick = {
                            selectedAnswer = option
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(
                                color = if (selectedAnswer == option) {
                                    Color.Magenta
                                } else {
                                    Color.Transparent
                                }
                            ),
                    ) {
                        Text(text = option)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$correctAnswers correct questions out of ${question.options.size} questions in total",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = {
                        if (selectedAnswer != null) {
                            val isCorrect = selectedAnswer == question.correct
                            if (isCorrect) {
                                correctAnswers++
                                buttonBackgroundColor = Color.Green
                            } else {
                                buttonBackgroundColor = Color.Red
                            }
                            currentQuestionIndex++
                            selectedAnswer = null
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .background(buttonBackgroundColor),
                    enabled = selectedAnswer != null
                ) {
                    Text(text = "Submit answer and next question")
                }

            }
        }
        else {
            Text(
                text = "All questions answered. Quiz completed!",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = "$correctAnswers correct questions out of ${com.example.quizaplication.model.questions.quizQuestions.size} questions in total",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

       //WORKING TEMPLATE
        /*Column(modifier = Modifier.padding(16.dp)) {
        quizQuestions.forEachIndexed { index, question ->
            Text(text = "${index + 1}. OPTIONS: ${question.options.joinToString(", ")}")
            Text(text = "${index + 1}. PROMPT: ${question.prompt}")
            Text(text = "${index + 1}. CORRECT: ${question.correct}")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }*/
}

@Preview
@Composable
fun MultiPreview() {
    lateinit var navController: NavHostController
    navController = rememberNavController()
    //MultipleChoice(navController)
}