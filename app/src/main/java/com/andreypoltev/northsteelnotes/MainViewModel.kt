package com.andreypoltev.northsteelnotes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreypoltev.northsteelnotes.data.Note
import com.andreypoltev.northsteelnotes.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val db: NoteDao
) : ViewModel() {

    val notes: Flow<List<Note>> = db.getAllNotes()

    val currentNote = mutableStateOf(Note())



    init {

        viewModelScope.launch(Dispatchers.IO) {
            if (db.getCount() == 0)
                db.insertNote(
                    Note(
                        title = "Title",
                        content = "При первом запуске в приложении должна быть одна заметка с текстом."
                    )
                )

        }
    }


    suspend fun getNoteById(id: Int): Note? {
        return db.getNoteById(id)
    }


    fun insertNote(title: String, content: String, imageUri: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertNote(Note(title = title, content = content))
        }
    }

    fun updateNote(id: Int, title: String, content: String, imageUri: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            db.updateNote(Note(id = id, title = title, content = content))
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteNote(note)
        }
    }

}