package com.macluczak.mywallet.data.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.macluczak.mywallet.ui.theme.*

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var message: String,
    var color: Int,
    var fav: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    
){


    companion object{
        val colorOfNote = listOf(BlueNote, YellowNote, PurpleNote, GreenNote)
        val colorOfNoteA = listOf(BlueNoteA, YellowNoteA, PurpleNoteA, GreenNoteA)
    }

}