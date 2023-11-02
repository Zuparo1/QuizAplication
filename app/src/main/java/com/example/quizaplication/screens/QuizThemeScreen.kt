package com.example.quizaplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.quizaplication.model.QuizCategory
import com.example.quizaplication.navigation.Screen
import com.example.quizaplication.navigation.TopNavBar

@Composable
fun QuizThemeScreen(
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar(navController)
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
                QuizCategory(4, "Programing"),
                QuizCategory(5, "English"),
                QuizCategory(6, "Norwegian"),
                QuizCategory(7, "Biology"),
                QuizCategory(8, "Chemistry"),
                QuizCategory(9, "Data security"),
                QuizCategory(10, "Mix"),
                QuizCategory(11, "AI"),)
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
    val categoryColors = listOf(
        Color(0xFFECEFF1),
        Color(0xFFFAF3E0),
        Color(0xFFF1FAEE),
        Color(0xFFE6E6E6),
        Color(0xFFD8D0C0)
    )
    val categoryColor = categoryColors[category.id % categoryColors.size]
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
               .clickable {navController.navigate(route =
               Screen.MultipleChoice.createRoute(category.name))} //new
        ,elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = categoryColor
        )
    ) {
        Text(
            text = category.name,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize(),
            style = TextStyle(fontSize = 18.sp)
        )
    }
}