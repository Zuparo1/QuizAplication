package com.example.quizaplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaplication.screens.HomeScreen

import com.example.quizaplication.screens.QuizThemeScreen
import com.example.quizaplication.screens.RandomTestScreen
import com.example.quizaplication.screens.SettingScreen


@Composable
fun SetUpNavGraph (navController : NavHostController){
    NavHost(navController = navController,
            startDestination = Screen.Home.route){
        composable(
                route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.QuizTheme.route
        ){
            QuizThemeScreen(navController = navController)
        }
        composable(
            route = Screen.Random.route
        ){
            RandomTestScreen(navController = navController)
        }
        composable(
            route = Screen.Setting.route
        ){
            SettingScreen(navController = navController)
        }

    }
}