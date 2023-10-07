package com.andreypoltev.northsteelnotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.mainScreen) {
        composable(Route.mainScreen) {
            MainScreen(viewModel, navController)

        }

        composable(Route.noteDetailsScreen) {
            NoteDetailsScreen(viewModel, navController)

        }
    }

}