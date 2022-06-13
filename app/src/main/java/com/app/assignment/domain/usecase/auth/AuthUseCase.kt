package com.app.assignment.domain.usecase.auth

data class AuthUseCase(
    val firebaseLoginUseCase: FirebaseLoginUseCase,
    val firebaseLogOutUseCase: FirebaseLogOutUseCase,
    val firebaseGetUserUseCase: FirebaseGetUserUseCase
)