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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()
    val showBottomMenu = remember {
        mutableStateOf(true)
    }


    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(showBottomMenu.value){


                BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    ),
                    backgroundColor = HippieBluelight
                ) {


                    BottomMenu(items = listOf(
                        BottomMenuContent("Home", "home_screen", R.drawable.ic_baseline_home_24),
                        BottomMenuContent("List", "second_page", R.drawable.ic_baseline_list_24),
                        BottomMenuContent("Notes", "third_page", R.drawable.ic_baseline_widgets_24)
                    ), navController = navController)

                }

            }

        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if(showBottomMenu.value) {

                fab(viewModel = viewModel, navController = navController){
                    showBottomMenu.value = false
                }
            }


//            FloatingActionButton(onClick = {navController.navigate(Screen.CreateScreen.withArgs())}) {
//
//            }


        })
    { it

        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(route = Screen.HomeScreen.route) {
                showBottomMenu.value = true
                HomeScreen(viewModel = viewModel, navController = navController)
            }
            composable(route = Screen.NoteDetail.route + "/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) { entry ->
                showBottomMenu.value = false
                NoteDetails(id = entry.arguments!!.getInt("id"), viewModel = viewModel)


            }
            composable(route = Screen.TaskDetail.route + "/{id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )) { entry ->
                showBottomMenu.value = false
                TaskDetails(id = entry.arguments!!.getInt("id"), viewModel = viewModel, navController = navController)


            }
            composable(route = Screen.SecondPage.route) {
                showBottomMenu.value = true
                SecondPage(viewModel = viewModel, navController = navController)
            }

            composable(route = Screen.CreateScreen.route) {
                showBottomMenu.value = false
                CreateScreen(viewModel = viewModel, navController = navController)
            }

            composable(route = Screen.ThirdPage.route) {
                showBottomMenu.value = true
                ThirdPage(viewModel, navController = navController)
            }
        }
    }

}
