package com.example.quizaplication.service.module

import com.example.quizaplication.service.AccountService
import com.example.quizaplication.service.QuizService
import com.example.quizaplication.service.impl.AccountServiceImpl
import com.example.quizaplication.service.impl.QuizServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideQuizService(impl: QuizServiceImpl): QuizService
}