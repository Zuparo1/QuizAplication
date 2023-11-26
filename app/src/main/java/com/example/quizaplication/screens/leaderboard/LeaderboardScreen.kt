package com.example.quizaplication.screens.leaderboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizaplication.R
import com.example.quizaplication.model.QuizTypeData
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun LeaderboardScreen(
    navController: NavController,
) {
    val pageTitle = "Leaderboard"

    val quizTypes = listOf(
        QuizTypeData("Multiple Choice","multipleChoiceQuiz"),
        QuizTypeData("True or False","trueOrFalse"),
        QuizTypeData("Text Input","textInput"),
        QuizTypeData("Multimedia","multiMedia"),
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController,pageTitle)
        Text(text = "Leaderboard",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            quizTypes.forEach { quizType ->
                item {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.buttonColor)
                        ),
                        onClick = {navController.navigate(route = Screen.LeaderboardUser.createLeaderboardRoute(quizType.path))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)) {
                        Text(
                            text = quizType.quizType,
                            style = TextStyle(fontSize = 18.sp),
                            modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}