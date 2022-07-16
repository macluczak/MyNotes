package com.macluczak.mywallet.data.task

import androidx.lifecycle.LiveData
import androidx.room.*
import com.macluczak.mywallet.data.note.Note

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>


    @Query("DELETE FROM task_table")
    fun deleteAllTasks()
}