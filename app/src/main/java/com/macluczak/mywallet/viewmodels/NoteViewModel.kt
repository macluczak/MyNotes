package com.macluczak.mywallet.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.note.NoteRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class NoteViewModel(application: Application):
AndroidViewModel(application){

    private var noteRepostitory: NoteRepository =
        NoteRepository(application)

    private var allNote: Deferred<LiveData<List<Note>>> =
        noteRepostitory.getAllNotesAsync()

    fun insertNote(note: Note){
        noteRepostitory.insertNote(note)
    }

    fun updateNote(note: Note){
        noteRepostitory.updateNote(note)
    }

    fun deleteNote(note: Note){
        noteRepostitory.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>> = runBlocking{
        allNote.await()
    }
    fun deleteAllNote(){
        noteRepostitory.deleteAllNotes()
    }


}