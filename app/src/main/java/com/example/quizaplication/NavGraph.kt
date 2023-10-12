package com.example.quizaplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaplication.screens.HomeScreen
import com.example.quizaplication.screens.RandomTestScreen

@Composable
fun SetUpNavGraph (navController : NavHostController){
    NavHost(navController = navController,
            startDestination = Screen.Home.route){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen()
        }
        composable(
            route = Screen.Random.route
        ){
            RandomTestScreen()
        }


    }
}