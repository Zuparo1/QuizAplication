package com.example.quizaplication

import com.example.quizaplication.navigation.Screen
import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun createRoute_isCorrect(){
        val collectionPath = "collectionPath"
        val documentPath = "documentPath"
        val difficulty = "dif"
        val expectedRoute = "$collectionPath/$documentPath/$difficulty"

        assertEquals(expectedRoute, Screen.Route.createRoute(documentPath = documentPath,
            collectionPath = collectionPath,
            difficulty = difficulty))
    }
    @Test
    fun createQuizRoute_isCorrect(){
        val documentPath = "history"
        val expectedRoute = "SelectQuizTypeScreen/$documentPath"

        assertEquals(expectedRoute, Screen.SelectQuizType.createQuizRoute(documentPath = documentPath))
    }

    @Test
    fun createLeaderBoardRoute_isCorrect(){
        val documentPath = "Jacob"
        val expectedRoute = "LeaderboardUser/$documentPath"

        assertEquals(expectedRoute, Screen.LeaderboardUser.createLeaderboardRoute(documentPath = documentPath))
    }

    @Test
    fun createGlobalLeaderBoardRoute_isCorrect(){
        val documentPath = "Global"
        val subjectPath = "math"
        val expectedRoute = "LeaderboardGlobal/$documentPath/$subjectPath"

        assertEquals(expectedRoute, Screen.LeaderboardGlobal.createLeaderboardGlobalRoute(
            documentPath = documentPath,
            subjectPath = subjectPath))
    }
}