package com.macluczak.mywallet.data.task

import android.app.Application
import androidx.lifecycle.LiveData
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.note.NoteDatabase
import kotlinx.coroutines.*

class TaskRepository(application: Application) {

    private var taskDao: TaskDao

    init{
        val database = TaskDatabase.getInstance(application.applicationContext)
        taskDao = database!!.taskDao()
    }

    fun insertTask(task: Task): Job =
    CoroutineScope(Dispatchers.IO).launch {
        taskDao.insert(task)
    }

    fun updateTask(task: Task): Job =
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.update(task)
        }

    fun deleteTask(task: Task): Job =
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.delete(task)
        }

    fun getAllTasks(): Deferred<LiveData<List<Task>>> =
        CoroutineScope(Dispatchers.IO).async {
            taskDao.getAllTasks()
        }

    fun deleteAllTasks(): Job =
        CoroutineScope(Dispatchers.IO).launch {
            taskDao.deleteAllTasks()
        }
}