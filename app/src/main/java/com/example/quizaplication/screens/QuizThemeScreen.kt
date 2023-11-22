package com.example.quizaplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.navigation.NavGraph
import com.example.quizaplication.R
import com.example.quizaplication.model.QuizCategory
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun QuizThemeScreen(
    navController: NavController,
) {
    val pageTitle = "Quiz Theme"

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        //.background((colorResource(id = R.color.primaryColor))),
        ,verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController, pageTitle)
        Text(text = "Choose Category",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            val categories = listOf(
                QuizCategory(1, "History"),
                QuizCategory(2, "Math"),
                QuizCategory(3, "Science"),
                QuizCategory(4, "Programming"),)
            categories.forEach { category ->
                item {
                    CategoryItem(
                        category = category,
                        navController
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: QuizCategory,
    navController: NavController
) {
    val categoryColor = colorResource(id = R.color.buttonColor)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
               /*.clickable {navController.navigate(route =
               Screen.SelectQuizType.createRoute("",category.name))} //new*/
            .clickable { navController.navigate(route = Screen.SelectQuizType.createQuizRoute( category.name)) }
        ,elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = categoryColor
        )
    ) {
        Text(
            color = Color.White,
            text = category.name,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}