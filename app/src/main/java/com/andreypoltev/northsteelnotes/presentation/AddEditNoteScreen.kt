package com.andreypoltev.northsteelnotes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.data.Note
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(viewModel: MainViewModel, navController: NavHostController, noteId: Int) {

    var title by remember {
        mutableStateOf("")
    }

    var content by remember {
        mutableStateOf("")
    }

    val coroutineScope = rememberCoroutineScope()

    var note by remember {
        mutableStateOf(Note())
    }

    LaunchedEffect(true) {
        coroutineScope.launch {
            val newNote = viewModel.getNoteById(noteId)
            if (newNote != null)
                note = newNote
            title = note.title
            content = note.content

        }
    }

    val ti = viewModel.currentNote.value



    Scaffold(topBar = {
        TopAppBar(title = {
            if (noteId == -1)
                Text(text = "Add new note")
            else
                Text(text = "Edit note")
        },
            actions = {

                if (title.isNotEmpty()) {
                    IconButton(onClick = {
                        if (noteId == -1)
                            viewModel.insertNote(title = title, content = content)
                        else
                            viewModel.updateNote(id = noteId, title = title, content = content)

                        navController.popBackStack()

                    }) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Add Note Button"
                        )
                    }
                }

            })

    }) {
        Column(
            Modifier.padding(
                bottom = it.calculateBottomPadding(),
                top = it.calculateTopPadding()
            )
        ) {
//            Text("Note id is: $noteId")
//            Text("Note title is: $title")
//            Text("Note content is: $content")
//            Text("Current note is: ${ti.toString()}")

//            BasicTextField(
//                value = title,
//                onValueChange = {title = it},
//                singleLine = true,
////                textStyle = MaterialTheme.typography.headlineLarge,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .onFocusChanged {
//////                onFocusChange(it)
////                    }
//            )

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = MaterialTheme.typography.headlineLarge,
                singleLine = true,
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )


//            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
//                textStyle = MaterialTheme.typography.headlineLarge,
//                singleLine = true,
                placeholder = {
                    Text(text = "Content")
                },
                modifier = Modifier.fillMaxSize(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

//            BasicTextField(
//                value = content,
//                onValueChange = {title = it},
//                singleLine = true,
////                textStyle = MaterialTheme.typography.headlineLarge,
////                modifier = Modifier
////                    .fillMaxWidth()
////                    .onFocusChanged {
//////                onFocusChange(it)
////                    }
//            )

        }

    }


}