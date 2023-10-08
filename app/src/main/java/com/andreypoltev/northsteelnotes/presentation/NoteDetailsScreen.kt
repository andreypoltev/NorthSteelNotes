package com.andreypoltev.northsteelnotes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.data.Note
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(viewModel: MainViewModel, navController: NavHostController, noteId: Int) {

    val coroutineScope = rememberCoroutineScope()

    val title by remember {
        mutableStateOf("Ямфвлф")
    }

    val content by remember {
        mutableStateOf("пкпукпукп")
    }

    var note by remember {
        mutableStateOf(Note())
    }

    LaunchedEffect(true) {
        coroutineScope.launch {
            val newNote = viewModel.getNoteById(noteId)
            if (newNote != null)
                note = newNote
        }
    }


//    val note = viewModel.currentNote.

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = note.title)
            },
            actions = {
                IconButton(onClick = { viewModel.upsertNote(title = title, content = content) }) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "Save Note Button")


                }

            }
        )

    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            Text(text = "Note id is: ${noteId}")
            Text(text = note.content)
            Text(text = note.dateUpdated)


        }


    }

//    Text(text = "Note Details Screen", Modifier.fillMaxSize())
}