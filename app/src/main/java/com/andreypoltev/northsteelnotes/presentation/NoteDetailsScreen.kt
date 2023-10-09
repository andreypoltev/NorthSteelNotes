package com.andreypoltev.northsteelnotes.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.data.Note
import com.andreypoltev.northsteelnotes.util.Route
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(viewModel: MainViewModel, navController: NavHostController, noteId: Int) {

    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    var title by remember {
        mutableStateOf("")
    }

    var note by remember {
        mutableStateOf(Note())
    }

    LaunchedEffect(true) {
        coroutineScope.launch {
            val newNote = viewModel.getNoteById(noteId)
            if (newNote != null)
                note = newNote

            title = note.title
//            content = note.content
        }
    }

//    val note = viewModel.currentNote.

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = note.title)
            },
            actions = {
                IconButton(onClick = { navController.navigate(Route.addEditNoteScreen + "/${note.id}") }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Note Button")


                }

                IconButton(onClick = {
                    viewModel.deleteNote(note)
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note Button"
                    )
                }
            }
        )

    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
        ) {

            if (note.imageUri != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .size(250.dp)
                    model = ImageRequest.Builder(context)
                        .data(File(context.filesDir, note.imageUri))
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                )
            }

            Text(text = note.content)

        }

    }

}