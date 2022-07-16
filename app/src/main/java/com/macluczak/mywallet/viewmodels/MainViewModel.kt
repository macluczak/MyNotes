package com.macluczak.mywallet.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.note.NoteRepository
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.data.task.TaskRepository
import com.macluczak.mywallet.navigation.Screen
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(application: Application):
AndroidViewModel(application){
    val editNote = MutableLiveData<Boolean>()
    val markAsDone = MutableLiveData<Boolean>()
    val searchWord = MutableLiveData<String>()
    val routeState = MutableLiveData<String>()
    lateinit var nowMonth: String
    val listOfDay = ArrayList<String>()
    val listofWeek = ArrayList<String>()


    init{
        editNote.value = false
        markAsDone.value = false
        routeState.value = Screen.HomeScreen.route
        searchWord.value = ""
        initCurrentDay()
           }

    fun initCurrentDay(){

        val sdfrM = SimpleDateFormat("MMMM")
        val sdfrD = SimpleDateFormat("dd")
        val sdfrW = SimpleDateFormat("EEE")

        val cM: Calendar = GregorianCalendar()
        cM.add(Calendar.MONTH, -0)
        nowMonth =  sdfrM.format(cM.time).toString()

        for(i in (-3).. 3){
            val cD: Calendar = GregorianCalendar()
            cD.add(Calendar.DAY_OF_MONTH, i)
            listOfDay.add(sdfrD.format(cD.time).toString())
            listofWeek.add(sdfrW.format(cD.time).toString())

        }

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

    fun asDone(){
        markAsDone.value = true
    }

    fun asDoneCancel(){
        markAsDone.value = false
    }

    fun getNoteEdit(): MutableLiveData<Boolean> = editNote

    fun getMarkTask(): MutableLiveData<Boolean> = markAsDone


}