package com.app.assignment.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.app.assignment.presentation.auth.AuthScreen
import com.app.assignment.presentation.games.GamesScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun NavGraph (
    navController: NavHostController,
    startDestination: String,
    signinClick: ()-> Unit,
    signOutClick: () -> Unit
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(
            route = Screen.AuthScreen.route
        ) {
            AuthScreen(
                navigateToGameScreen = {
                    navController.navigate(Screen.GamesScreen.route)
                }, onSignIn = signinClick
            )
        }
        composable(
            route = Screen.GamesScreen.route
        ) {
            GamesScreen(signOutClick)
        }
    }
}