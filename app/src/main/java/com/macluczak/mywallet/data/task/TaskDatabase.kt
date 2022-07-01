package com.macluczak.mywallet.data.task

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.macluczak.mywallet.data.note.NoteDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao():  TaskDao


    companion object{

        private var instance: TaskDatabase? = null



        fun getInstance(context: Context): TaskDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    TaskDatabase::class.java,
                    "task_table"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }


}