package com.macluczak.mywallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.viewmodels.MainViewModel


@Composable

fun NoteDetails(id: Int, viewModel: MainViewModel){
    val note = viewModel.getAllNotes().value?.singleOrNull{it.id == id}
    val colorOfNote = remember {
        mutableStateOf(note!!.color)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Note.colorOfNote[colorOfNote.value])){



        Column(){


            Text(text = note?.id.toString())
            Text(text = note?.title.toString())
            ColorPicker(){
                colorOfNote.value = it
            }

            Text(text = note?.message.toString())

        }

    }



}