package com.andreypoltev.northsteelnotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.andreypoltev.northsteelnotes.presentation.MainScreen
import com.andreypoltev.northsteelnotes.presentation.NoteDetailsScreen
import com.andreypoltev.northsteelnotes.util.Route

@Composable
fun Navigation(viewModel: MainViewModel, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Route.mainScreen) {
        composable(Route.mainScreen) {
            MainScreen(viewModel, navController)

        }

        composable(
            route = Route.noteDetailsScreen + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.IntType
                }
            )

            ) { entry ->
            NoteDetailsScreen(viewModel, navController, entry.arguments?.getInt("id") ?: -1)

        }

//        composable(route = Route.addEditNoteScreen + "/{id}",
//            arguments = listOf(
//                navArgument(name = "id") {
//                    type = NavType.IntType
//                }
//            )
//
//        ) {
//            AddEditNoteScreen(viewModel, navController, entry.arguments?.getInt("id") ?: -1)
//
//        }


    }

}