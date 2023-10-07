package com.andreypoltev.northsteelnotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
data class Note(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val dateUpdated: String = getDateCreated(),
    val imageUri: String? = null
)

fun getDateCreated(): String {
    // Get the current date and time
    val currentDateTime = LocalDateTime.now()

    // Define a format for displaying the date and time
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // Format the current date and time as a string
    val formattedDateTime = currentDateTime.format(formatter)

//    // Doesn't require API 26
//    val currentDateTime = Calendar.getInstance().time
//    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("RU"))
//    val formattedDateTime = formatter.format(time)

    return formattedDateTime
}