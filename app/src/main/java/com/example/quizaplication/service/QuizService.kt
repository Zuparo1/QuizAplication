package com.example.quizaplication.service

import com.example.quizaplication.model.Quiz

interface QuizService {
    suspend fun getQuiz(name: String): Quiz?
}