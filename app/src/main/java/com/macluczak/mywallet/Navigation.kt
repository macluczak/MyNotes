package com.macluczak.mywallet

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgs
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.NoteDetail.route + "/{id}", arguments = listOf(
            navArgument("id"){
                type = NavType.IntType
                defaultValue = 0
                nullable = false
            }
        )){
            entry -> 
            NoteDetails(id = entry.arguments!!.getInt("id"))


        }
    }
}
