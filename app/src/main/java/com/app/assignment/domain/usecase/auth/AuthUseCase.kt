package com.app.assignment.domain.usecase.auth

data class AuthUseCase(
    val isUserAuthenticated: IsUserAuthenticated,
    val storeToken: StoreToken
)