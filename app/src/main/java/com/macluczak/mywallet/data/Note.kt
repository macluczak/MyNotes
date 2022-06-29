package com.macluczak.mywallet.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var message: String,
    var time: Int,
    var color: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}