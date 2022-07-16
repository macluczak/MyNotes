package com.macluczak.mywallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun TaskDetails(id: Int, viewModel: MainViewModel){

    val task = viewModel.getAllTasks().value?.singleOrNull{it.id == id}

    Column() {
        Text(text = task?.id.toString())
        Text(text = task?.title.toString())
        Text(text = task?.state.toString())
        Text(text = task?.description.toString())
        Text(text = task?.wholeDay.toString())
        Row() {
            Text(text = task?.startDate.toString())
            Text(text = task?.startTime.toString())
        }
        Row() {
            Text(text = task?.endDate.toString())
            Text(text = task?.endTime.toString())
        }


    }




}