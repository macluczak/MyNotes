package com.macluczak.mywallet.data.note

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
}