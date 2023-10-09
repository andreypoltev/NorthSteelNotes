//package com.andreypoltev.northsteelnotes.util
//
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.runtime.Composable
//
//@Composable
//fun Trash() {
//
//    OutlinedTextField(
//        value = title,
//        onValueChange = { title = it },
//        textStyle = MaterialTheme.typography.headlineLarge,
//        singleLine = true,
//        placeholder = {
//            Text(text = "Title")
//        },
//        modifier = Modifier.fillMaxWidth(),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Color.Transparent,
//            unfocusedBorderColor = Color.Transparent
//        )
//    )
//
//    OutlinedTextField(
//        value = content,
//        onValueChange = { content = it },
//        textStyle = MaterialTheme.typography.headlineLarge,
//        singleLine = true,
//        placeholder = {
//            Text(text = "Content")
//        },
//        modifier = Modifier.fillMaxSize(),
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            focusedBorderColor = Color.Transparent,
//            unfocusedBorderColor = Color.Transparent
//        )
//    )
//
//}