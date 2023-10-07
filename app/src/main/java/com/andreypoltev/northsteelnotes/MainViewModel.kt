package com.andreypoltev.northsteelnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.andreypoltev.northsteelnotes.data.Note
import com.andreypoltev.northsteelnotes.data.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val db: NoteDao
) : ViewModel() {

    val notes: Flow<List<Note>> = db.getAllNotes()

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            db.insertNote(Note(title = "Title", content = "Content"))

        }
    }

//    fun getNoteById(id: Int): Note? {
//        viewModelScope.launch(Dispatchers.IO) {
//            return db.getNoteById(id)
//        }
//
//    }

    fun upsertNote(title: String, content: String, imageUri: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            db.upsertNote(Note(title = title, content = content))
        }
    }

//    @Update
//    suspend fun updateNote(note: Note)

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            db.deleteNote(note)
        }
    }

}