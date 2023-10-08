package com.andreypoltev.northsteelnotes

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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


    val currentNote = mutableStateOf(Note())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            db.upsertNote(Note(title = "오늘 생긴 일", content = "우늘은 엄청 어려워요. 어떡하죠?"))

        }
    }

    suspend fun getNoteById(id: Int): Note? {

        return db.getNoteById(id)
    }


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