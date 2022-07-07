package com.macluczak.mywallet.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.note.NoteRepository
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.data.task.TaskRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application):
AndroidViewModel(application){
    val editNote = MutableLiveData<Boolean>()
    val searchWord = MutableLiveData<String>()

    init{
        editNote.value = false
        searchWord.value = ""
    }




    // NOTE
    private var noteRepostitory: NoteRepository =
        NoteRepository(application)

    private var taskRepository: TaskRepository =
        TaskRepository(application)

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


    //TASK
    private var allTasks: Deferred<LiveData<List<Task>>> =
        taskRepository.getAllTasks()

    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }

    fun updateTask(task: Task){
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task){
        taskRepository.deleteTask(task)
    }

    fun getAllTasks(): LiveData<List<Task>> = runBlocking{
        allTasks.await()
    }
    fun deleteAllTasks(){
        taskRepository.deleteAllTasks()
    }

    fun noteEditable(){
        editNote.value = true
    }

    fun noteUneditable(){
        editNote.value = false
    }

    fun getNoteEdit(): MutableLiveData<Boolean> = editNote


}