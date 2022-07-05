package com.macluczak.mywallet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskDetails(id: Int){

    Text(text = id.toString())
}