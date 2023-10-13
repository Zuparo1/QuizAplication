package com.example.quizaplication.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.quizaplication.navigation.TopNavBar
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.quizaplication.screens.quiz.questions.quizQuestions
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun MultipleChoiceQuizScreen(
    navController: NavController
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var buttonBackgroundColor by remember { mutableStateOf(Color.Transparent) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
    ) {
        TopNavBar(navController)

        Text(
            text = "History: Multiple Choice",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(16.dp)
        )
        if (currentQuestionIndex < quizQuestions.size) {

            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                val question = quizQuestions[currentQuestionIndex]

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = question.questionText,
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                    )
                }

                question.answerChoices.forEach { option ->
                    Button(
                        onClick = {
                            selectedAnswer = option
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = option)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "$correctAnswers correct questions out of ${quizQuestions.size} questions in total",
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
                            val isCorrect = selectedAnswer == question.correctAnswer
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
        } else {
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
                text = "$correctAnswers correct questions out of ${quizQuestions.size} questions in total",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )


        }
    }
}

@Preview
@Composable
fun MultipleChoiceQuizScreenPreview() {
    lateinit var navController: NavHostController
    navController = rememberNavController()
    MultipleChoiceQuizScreen(navController)
}
