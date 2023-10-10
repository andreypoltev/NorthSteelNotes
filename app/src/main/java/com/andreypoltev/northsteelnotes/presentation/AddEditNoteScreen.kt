package com.andreypoltev.northsteelnotes.presentation

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.data.Note
import com.andreypoltev.northsteelnotes.util.saveImageToInternalStorage
import kotlinx.coroutines.launch
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(viewModel: MainViewModel, navController: NavHostController, noteId: Int) {

    val context = LocalContext.current

    var imageUri: String? by remember {
        mutableStateOf(null)
    }

    var fileName by remember {
        mutableStateOf<String?>(null)
    }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            Log.d("PhotoPicker", "Selected URI: $it")
            imageUri = it.toString()
            fileName = saveImageToInternalStorage(context, it.toString())
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

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
            imageUri = note.imageUri

            if (note.imageUri != null)
                fileName = note.imageUri

        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            if (noteId == -1)
                Text(text = "Add new note")
            else
                Text(text = "Edit note")
        },
            actions = {

                IconButton(onClick = {

                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }) {
                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = "Add Image Button"
                    )
                }

                if (title.isNotEmpty()) {
                    IconButton(onClick = {
//                        if (imageUri != null)
//                            fileName = saveImageToInternalStorage(context = context, uri = imageUri.toString())
                        if (noteId == -1) {
                            viewModel.insertNote(title = title, content = content, imageUri = fileName)
//                            debug += fileName + "\n"
                        }
                        else {
                            viewModel.updateNote(id = noteId, title = title, content = content, imageUri = fileName)
//                            debug += fileName + "\n"
                        }

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

        LazyColumn(
            Modifier.padding(
                bottom = it.calculateBottomPadding(),
                top = it.calculateTopPadding()
            )
        ) {

            item {

                if (fileName != null) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                photoPicker.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )

                            },
//                        .size(250.dp)
                        model = ImageRequest.Builder(context)
//                            .data(File(context.filesDir, note.imageUri))
                            .data(File(context.filesDir, fileName))
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Fit,
                    )
                }
            }

            item {
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


            }

            item {
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
            }






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




//            Spacer(modifier = Modifier.height(16.dp))



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