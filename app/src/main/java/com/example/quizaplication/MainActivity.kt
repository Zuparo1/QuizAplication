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
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizaplication.screens.HomeScreen
import com.example.quizaplication.screens.RandomTestScreen
import com.example.quizaplication.ui.theme.QuizAplicationTheme


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  MaterialTheme.colorScheme.background
                ) {
                    //HomeScreen(
                        //onCategoryClick = {}
                   // )
                    navController = rememberNavController()
                    SetUpNavGraph(navController = navController)
                }
            }
        }
    }
}



@Composable
fun Navigation(
    // navController : NavHostController = rememberNavController(),
     startDestination: String = "home"
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){
       // composable("home") {HomeScreen(navController::navigate)}
        composable("test") {RandomTestScreen()}
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    lateinit var navController: NavHostController
    QuizAplicationTheme {
       /*HomeScreen(
            navController::navigate
           // onCategoryClick = {}
        )*/

        //Navigation()
        navController = rememberNavController()
        SetUpNavGraph(navController = navController)



    }
}