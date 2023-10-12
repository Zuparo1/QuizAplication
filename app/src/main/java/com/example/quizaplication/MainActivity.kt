package com.example.quizaplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizaplication.screens.HomeScreen
import com.example.quizaplication.ui.theme.QuizAplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        categories = categories,
                        onCategoryClick = {}
                    )
                }
            }
        }
    }
}


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





@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    QuizAplicationTheme {
        HomeScreen(
            categories = categories,
            onCategoryClick = {}
        )

    }
}