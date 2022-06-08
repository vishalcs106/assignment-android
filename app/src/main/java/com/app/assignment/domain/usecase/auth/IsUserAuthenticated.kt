package com.app.assignment.domain.usecase.auth

import com.app.assignment.domain.repository.auth.AuthRepository

class IsUserAuthenticated(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}