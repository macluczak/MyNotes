package com.macluczak.mywallet.data.note

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class NoteRepository(application: Application) {

    private var noteDao: NoteDao

    init {
        val database = NoteDatabase.getInstance(application.applicationContext)
        noteDao = database!!.noteDao()
    }

    fun insertNote(note: Note): Job =
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }

    fun updateNote(note: Note): Job =
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }

    fun deleteNote(note: Note): Job =
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.delete(note)
        }

    fun getAllNotesAsync(): Deferred<LiveData<List<Note>>> =
        CoroutineScope(Dispatchers.IO).async {
            noteDao.getAllNotes()
        }

    fun deleteAllNotes(): Job=
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.deleteAllNotes()
        }


}