package com.example.quizaplication.navigation

sealed class Screen(val route : String){
    object Home: Screen(route = "HomeScreen")
    object QuizTheme: Screen(route = "QuizThemeScreen")
    object Leaderboard: Screen(route = "Leaderboard")
    object LeaderboardUser: Screen(route = "LeaderboardUser/{documentPath}")
    object LeaderboardGlobal: Screen(route = "LeaderboardGlobal/{documentPath}/{subjectPath}")
    object Setting: Screen(route = "SettingScreen")
    object LogIn : Screen(route = "LoginScreen")
    object Register : Screen(route = "SignUpScreen")

    object SelectQuizType : Screen(route = "SelectQuizTypeScreen/{documentPath}")
    object MultipleChoice : Screen(route = "MultipleChoice/{documentPath}")
    object TrueOrFalse : Screen(route = "TrueOrFalse/{documentPath}")
    object TextInput : Screen(route = "TextInput/{documentPath}")
    object MultiMedia : Screen(route = "MultiMedia/{documentPath}")
    object Route : Screen(route = "{collectionPath}/{documentPath}")
    fun createRoute(collectionPath: String ,documentPath : String): String{
        return "$collectionPath/$documentPath"
    }
    fun createQuizRoute (documentPath: String): String {
        return "SelectQuizTypeScreen/$documentPath"
    }
    fun createLeaderboardRoute(documentPath: String): String {
        return "LeaderboardUser/$documentPath"
    }
    fun createLeaderboardGlobalRoute(documentPath: String, subjectPath: String): String {
        return "LeaderboardGlobal/$documentPath/$subjectPath"
    }
}
