package com.example.quizaplication.screens.quiz.type

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizaplication.R
import com.example.quizaplication.navigation.TopNavBar
import com.example.quizaplication.screens.viewModel.TrueOrFalseViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun TrueOrFalse(navController: NavController, documentPath : String, difficulty: String) {
    val pageTitle = "True or False"

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<Boolean?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var buttonBackgroundColor by remember { mutableStateOf(Color.Transparent) }
    val options = listOf(true, false)
    val firebaseAuth = FirebaseAuth.getInstance()
    val user = firebaseAuth.currentUser

    val viewModel: TrueOrFalseViewModel = hiltViewModel()
    val quizQuestions by viewModel.trueOrFalse.observeAsState(initial = emptyList())


    LaunchedEffect(Unit){
        viewModel.fetchQuizData("TrueOrFalse",documentPath + difficulty)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
    ){
        TopNavBar(navController, pageTitle)
        Text(
            text = documentPath,
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)

        )
        if (currentQuestionIndex < quizQuestions.size){
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )

            ){
                val question = quizQuestions[currentQuestionIndex]
                Column(

                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ){
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
               for(option in options) {
                    Button(
                        onClick = {
                            selectedAnswer = option
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                        ,colors = ButtonDefaults.buttonColors(
                            containerColor =
                            if (selectedAnswer == option)
                                colorResource(id = R.color.buttonPressedColor)
                            else
                                colorResource(id = R.color.buttonColor)
                        ),
                    ) {
                        Text(text = option.toString())
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
                            val isCorrect = selectedAnswer == question.isTrue
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
                    colors =  ButtonDefaults.buttonColors(colorResource(id = R.color.buttonColor)),
                    enabled = selectedAnswer != null
                ) {
                    Text(text = "Submit answer and next question")
                }
            }
        }
        else {
            if (user != null) {
                when (difficulty) {
                    "Beginner" -> viewModel.savePointsToDatabase(user.uid, documentPath, difficulty, correctAnswers)
                    "Intermediate" -> viewModel.savePointsToDatabase(user.uid, documentPath, difficulty, correctAnswers * 2)
                    "Hard" -> viewModel.savePointsToDatabase(user.uid, documentPath, difficulty, correctAnswers * 3)
                }
            }
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
            if (correctAnswers == quizQuestions.size && quizQuestions.isNotEmpty()){
                Column (modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "All Correct, Good Job!")
                    Row(modifier = Modifier.padding(16.dp)) {
                        Icon(Icons.Filled.Star, contentDescription ="Star")
                        Icon(Icons.Filled.Star, contentDescription ="Star")
                        Icon(Icons.Filled.Star, contentDescription ="Star")
                        Icon(Icons.Filled.Star, contentDescription ="Star")
                        Icon(Icons.Filled.Star, contentDescription ="Star")
                    }
                }
            }
        }
    }
}

