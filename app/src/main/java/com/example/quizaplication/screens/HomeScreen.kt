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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizaplication.QuizCategory
import com.example.quizaplication.TopNavBar

@Composable
fun HomeScreen(
    categories: List<QuizCategory>,
    onCategoryClick: (QuizCategory) -> Unit

) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopNavBar()
        Text(text = "Choose Category",
            style = TextStyle(
                fontSize = 46.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            categories.forEach { category ->
                item {
                    CategoryItem(
                        category = category,
                        onCategoryClick = { onCategoryClick(category) }
                    )
                }
            }
        }

    }
}

@Composable
fun CategoryItem(
    category: QuizCategory,
    onCategoryClick: (QuizCategory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCategoryClick(category) }
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