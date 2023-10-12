package com.example.quizaplication

sealed class Screen(val route : String){
    object Home: Screen(route = "HomeScreen")
    object Random: Screen(route = "RandomScreen")
}
