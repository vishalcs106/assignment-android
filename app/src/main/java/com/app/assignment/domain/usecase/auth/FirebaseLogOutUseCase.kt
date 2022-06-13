package com.app.assignment.domain.usecase.auth

import com.app.assignment.domain.repository.auth.AuthRepository

class FirebaseLogOutUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.logoutFromFirebase()
}