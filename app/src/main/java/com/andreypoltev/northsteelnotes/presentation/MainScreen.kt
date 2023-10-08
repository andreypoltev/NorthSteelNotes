package com.andreypoltev.northsteelnotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.andreypoltev.northsteelnotes.MainViewModel
import com.andreypoltev.northsteelnotes.util.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {

    val notes = viewModel.notes.collectAsState(emptyList())

    var currentNoteId by remember {
        mutableStateOf(-1)
    }

    var isDeleting by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Notes") },
                actions = {

                    IconButton(onClick = { navController.navigate(Route.addEditNoteScreen + "/-1") }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Note Button"
                        )
                    }

                    IconButton(onClick = { isDeleting = !isDeleting }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Delete Notes List Button"
                        )
                    }


                })
        }) {


        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()


                ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(notes.value) { note ->


                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        navController.navigate(Route.noteDetailsScreen + "/${note.id}")

                    }, colors = CardDefaults.cardColors(

                        containerColor = Color.Transparent,
                    )
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp, top = 4.dp)
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colors.onSurface,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = note.content,
//                            style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colors.onSurface,
                            maxLines = 10,
                            overflow = TextOverflow.Ellipsis
                        )

                        if (isDeleting) {
                            IconButton(
                                onClick = { viewModel.deleteNote(note) },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete note",
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                            }

                        }

//

                    }


                }

            }

        }

    }
}