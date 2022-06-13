package com.app.assignment.domain.usecase.auth

import com.app.assignment.domain.repository.auth.AuthRepository

class FirebaseLoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(token: String) = repository.loginToFirebase(token)
}

