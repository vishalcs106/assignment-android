package com.app.assignment.presentation.navigation

sealed class Screen(val route: String) {
    object AuthScreen: Screen("AUTH_SCREEN")
    object GamesScreen: Screen("GAMES_SCREEN")
}