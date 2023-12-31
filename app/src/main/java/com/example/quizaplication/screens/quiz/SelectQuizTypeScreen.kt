package com.example.quizaplication.screens.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizaplication.R
import com.example.quizaplication.model.QuizTypeData
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun SelectQuizTypeScreen(navController: NavController
                         , documentPath : String){
    val pageTitle = "Quiz Type"

    var selectedQuizType by remember { mutableStateOf<QuizTypeData?>(null) }
    var selectedDifficulty by remember { mutableStateOf<String?>(null) }

    var err by remember { mutableStateOf<Boolean?> (false)}

    val quizTypes = listOf(
        QuizTypeData("Multiple Choice","MultipleChoice"),
        QuizTypeData("True or False","TrueOrFalse"),
        QuizTypeData("Text Input","TextInput"),
        QuizTypeData("Multimedia","MultiMedia"),
        )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController,pageTitle)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Choose Difficulty",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            val difficulties = listOf(
                ("Beginner"), ("Intermediate"), ("Hard"))
            difficulties.forEach { difficulty ->
                item {
                    DifficultyLevel(
                        difficulty = difficulty,
                        isSelected = difficulty == selectedDifficulty,
                        onSelected = { selectedDifficulty = it}

                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Choose Quiz-Type",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            quizTypes.forEach { quizType ->
                item {
                    QuizType(
                        category = quizType,
                        isSelected = quizType == selectedQuizType,
                        onSelected = { selectedQuizType = it}

                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.buttonColor)),
            onClick = {
                if (selectedQuizType != null && selectedDifficulty != null) {
                    err = false
                    navController.navigate(route =
                    Screen.Route.createRoute(selectedQuizType!!.path, documentPath, selectedDifficulty!!))
                }
                else{
                    err = true
                }
            }


        ) {
            Text("Play Quiz")
        }
        if (err == true){
            Text(text = "Please Select Quiz-Type and Difficulty",
            )
        }
    }
}

@Composable
fun QuizType(
    category: QuizTypeData, isSelected: Boolean, onSelected: (QuizTypeData) -> Unit
) {
    val categoryColor = if (isSelected){
        colorResource(id = R.color.buttonPressedColor)
    }else{
        colorResource(id = R.color.buttonColor)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(category) }
        ,elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = categoryColor
        )
    ) {
        Text(
            text = category.quizType,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            style = TextStyle(fontSize = 18.sp),
            color = Color.White
        )
    }
}

@Composable
fun DifficultyLevel(
    difficulty: String, isSelected: Boolean, onSelected: (String) -> Unit
) {
    val categoryColor = if (isSelected){
        colorResource(id = R.color.buttonPressedColor)
    }else{
        colorResource(id = R.color.buttonColor)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSelected(difficulty) } //new
        ,elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = categoryColor
        )
    ) {
        Text(
            text = difficulty,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            style = TextStyle(fontSize = 18.sp),
            color = Color.White
        )
    }
}

