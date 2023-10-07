package com.andreypoltev.northsteelnotes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun NoteDetailsScreen(viewModel: MainViewModel, navController: NavHostController) {
    Text(text = "Note Details Screen", Modifier.fillMaxSize())
}