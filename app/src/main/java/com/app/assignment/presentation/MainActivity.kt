package com.app.assignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import com.app.assignment.presentation.auth.AuthViewModel
import com.app.assignment.presentation.navigation.NavGraph
import com.app.assignment.presentation.navigation.Screen
import com.app.assignment.ui.theme.AssignmentTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()
    private lateinit var mAuth: FirebaseAuth

    companion object {
        const val PERMISSION_REQUEST_STORAGE = 0
    }


    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentTheme {
                navController = rememberAnimatedNavController()
                val loginStatus = checkAuthStatus()
                val startDestination = if(loginStatus) Screen.GamesScreen.route else Screen.AuthScreen.route
                NavGraph(navController, startDestination){
                    logout()
                }
            }

        }
        initAuth()
    }

    private fun initAuth() {
        mAuth = FirebaseAuth.getInstance()
    }

    private fun checkAuthStatus(): Boolean {
        return viewModel.getUser() != null
    }

    private fun logout(){
        navController.navigate(Screen.AuthScreen.route) {
            popUpTo(Screen.GamesScreen.route) {
                inclusive = true
            }
        }
    }



}

