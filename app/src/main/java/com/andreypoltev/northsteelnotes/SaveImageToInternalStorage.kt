package com.andreypoltev.northsteelnotes

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun saveImageToInternalStorage(context: Context, uri: String): String {

    // Get the current date and time
    val currentDateTime = LocalDateTime.now()

    // Define a format for displaying the date and time
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")

    // Format the current date and time as a string
    val formattedDateTime = currentDateTime.format(formatter)

    val fileName = "$formattedDateTime.jpg"

    val inputStream = context.contentResolver.openInputStream(Uri.parse(uri))
    val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }

    return fileName




}