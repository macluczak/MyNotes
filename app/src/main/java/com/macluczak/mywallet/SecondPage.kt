package com.macluczak.mywallet

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun SecondPage(viewModel: MainViewModel, navController: NavController){

    Text(text = "second page")
}