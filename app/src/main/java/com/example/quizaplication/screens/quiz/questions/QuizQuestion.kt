package com.example.quizaplication.screens.quiz.questions


data class QuizQuestion(
    val questionText: String,
    val answerChoices: List<String>,
    val correctAnswer: String
)

val quizQuestions = listOf(
    QuizQuestion(
        "Who was the first President of the United States?",
        listOf("Thomas Jefferson", "John Adams", "George Washington", "Abraham Lincoln"),
        "George Washington"
    ),
    QuizQuestion(
        "In which year did Christopher Columbus first voyage to the Americas?",
        listOf("1492", "1607", "1776", "1865"),
        "1492"
    ),
    QuizQuestion(
        "What event marked the beginning of World War I?",
        listOf("The assassination of Archduke Franz Ferdinand", "The signing of the Treaty of Versailles", "The Boston Tea Party", "The Great Depression"),
        "The assassination of Archduke Franz Ferdinand"
    ),
    QuizQuestion(
        "Who was the first female Prime Minister of the United Kingdom?",
        listOf("Margaret Thatcher", "Queen Elizabeth II", "Theresa May", "Angela Merkel"),
        "Margaret Thatcher"
    ),

)