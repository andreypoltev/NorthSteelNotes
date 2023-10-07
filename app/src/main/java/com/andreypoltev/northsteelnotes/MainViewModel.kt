package com.andreypoltev.northsteelnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreypoltev.northsteelnotes.data.Note
import com.andreypoltev.northsteelnotes.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val db: NoteDao
): ViewModel() {

    val notes: Flow<List<Note>> = db.getAllNotes()

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            db.insertNote(Note(title = "Title", content = "Content"))


        }
    }

}