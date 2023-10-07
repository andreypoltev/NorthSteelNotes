package com.andreypoltev.northsteelnotes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    
    val notes = viewModel.notes.collectAsState(emptyList())

    Scaffold {
        LazyColumn(
            Modifier
                .fillMaxSize().padding(top = it.calculateTopPadding())) {
            items(notes.value) {
                Text(text = it.title)
                Text(text = it.content)
                Text(text = it.id.toString())

            }

        }

    }
    




}