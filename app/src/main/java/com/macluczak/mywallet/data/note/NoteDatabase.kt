package com.macluczak.mywallet.data.note

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note:: class], version = 8)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

//    singleton
    companion object{
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    NoteDatabase::class.java,
                    "note_table"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}