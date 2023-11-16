package com.example.quizaplication.screens.quiz.type

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizaplication.R
import com.example.quizaplication.navigation.TopNavBar
import com.example.quizaplication.screens.viewModel.MultipleChoiceViewModel
import com.example.quizaplication.screens.viewModel.TextInputViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(navController: NavController, documentPath : String) {
    val pageTitle = "Quiz"

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var correctAnswers by remember { mutableStateOf(0) }
    var buttonBackgroundColor by remember { mutableStateOf(Color.Transparent) }
    var textInput by remember { mutableStateOf("") }

    val viewModel: TextInputViewModel = viewModel()
    val quizQuestions by viewModel.textInput.observeAsState(initial = emptyList())


    LaunchedEffect(Unit){
        viewModel.fetchQuizData("TextInput",documentPath)
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
                TextField(
                    value = textInput,
                    onValueChange = {textInput = it},
                    label = { Text(text = "Enter your answer") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )


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
                        selectedAnswer = textInput.uppercase()
                        val isCorrect = selectedAnswer == question.answer.uppercase()
                        if (isCorrect) {
                            correctAnswers++
                            buttonBackgroundColor = Color.Green
                        } else {
                            buttonBackgroundColor = Color.Red
                        }
                        currentQuestionIndex++
                        selectedAnswer = null
                        textInput = ""
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .background(buttonBackgroundColor),
                    colors =  ButtonDefaults.buttonColors(colorResource(id = R.color.buttonColor)),
                    enabled = selectedAnswer != ""
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
