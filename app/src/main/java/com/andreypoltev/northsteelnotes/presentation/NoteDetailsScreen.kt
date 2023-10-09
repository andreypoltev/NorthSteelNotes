package com.andreypoltev.northsteelnotes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.util.Route
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(viewModel: MainViewModel, navController: NavHostController, id: Int) {

    val currentNote = viewModel.currentNote

    viewModel.updateCurrentNote(id)

    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = viewModel.currentNote.value.title)
            },
            actions = {
                IconButton(onClick = { navController.navigate(Route.addEditNoteScreen + "/$id") }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Note Button")


                }

                IconButton(onClick = {
                    viewModel.deleteNote(viewModel.currentNote.value)
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

            if (viewModel.currentNote.value.imageUri != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(),
//                        .size(250.dp)
                    model = ImageRequest.Builder(context)
                        .data(File(context.filesDir, viewModel.currentNote.value.imageUri))
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,
                )
            }

            Text(text = viewModel.currentNote.value.content)

        }

    }

}