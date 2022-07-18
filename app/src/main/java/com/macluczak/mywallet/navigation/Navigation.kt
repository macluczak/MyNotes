package com.macluczak.mywallet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.macluczak.mywallet.bottom_menu.BottomMenu
import com.macluczak.mywallet.bottom_menu.BottomMenuContent
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.BlueNoteDark
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(viewModel = viewModel, navController = navController)
            }
            composable(route = Screen.NoteDetail.route + "/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) { entry ->
                NoteDetails(id = entry.arguments!!.getInt("id"), viewModel = viewModel)


            }
            composable(route = Screen.TaskDetail.route + "/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) { entry ->
                TaskDetails(id = entry.arguments!!.getInt("id"), viewModel= viewModel)


            }
            composable(route = Screen.SecondPage.route) {
                SecondPage(viewModel = viewModel, navController = navController)
            }

            composable(route = Screen.CreateScreen.route) {
                CreateScreen(viewModel = viewModel, navController = navController)
            }

            composable(route = Screen.ThirdPage.route){
                ThirdPage(viewModel, navController = navController)
            }
        }

}
