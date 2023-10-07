package com.andreypoltev.northsteelnotes

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {

    val notes = viewModel.notes.collectAsState(emptyList())

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate(Route.noteDetailsScreen) }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")

        }
    }) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = (it.calculateTopPadding()),
                    bottom = 16.dp



                ), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(notes.value) { note ->


                Card(Modifier.fillMaxWidth()) {

                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = note.content,
                        style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colors.onSurface,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )

                    IconButton(
                        onClick = { },
                    modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete note",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                }

            }

        }

    }
}