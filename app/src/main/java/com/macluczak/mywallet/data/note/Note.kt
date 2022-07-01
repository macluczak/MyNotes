package com.macluczak.mywallet.data.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.macluczak.mywallet.ui.theme.*

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var message: String,
    var time: Int,
    var color: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object{
        val taskState = listOf(YellowNote, BlueNote, PurpleNote, GreenNote)
    }
}