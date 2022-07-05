package com.macluczak.mywallet.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.macluczak.mywallet.ui.theme.GreenDone
import com.macluczak.mywallet.ui.theme.OrangeProgress
import com.macluczak.mywallet.ui.theme.Purple200
import com.macluczak.mywallet.ui.theme.RedToDO

@Entity(tableName = "task_table")
data class Task(
    var title: String,
    var description: String,
    var state: Int,
    var startDate: Long,
    var endDate: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
) {


    companion object{
        val taskState = listOf(RedToDO, OrangeProgress, GreenDone)
    }
}