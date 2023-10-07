package com.andreypoltev.northsteelnotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = Route.mainScreen) {
        composable(Route.mainScreen) {
            MainScreen(viewModel = viewModel)

        }

        composable(Route.noteDetails) {

        }
    }

}