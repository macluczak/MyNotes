package com.macluczak.mywallet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.macluczak.mywallet.bottom_menu.BottomMenu
import com.macluczak.mywallet.bottom_menu.BottomMenuContent
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.BlueNoteDark
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun SecondPage(viewModel: MainViewModel, navController: NavController){

    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(navController.currentDestination?.route != Screen.CreateScreen.route){

                BottomAppBar(
                    cutoutShape = MaterialTheme.shapes.small.copy(
                        CornerSize(percent = 50)
                    ),
                    backgroundColor = BlueNoteDark
                ) {


                    BottomMenu(items = listOf(
                        BottomMenuContent("Home", "home_screen", R.drawable.ic_baseline_home_24),
                        BottomMenuContent("List", "second_page", R.drawable.ic_baseline_list_24)
                    ), navController = navController)

                }

            }

        },
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if(navController.currentDestination?.route != Screen.CreateScreen.route) {
                fab(viewModel = viewModel, navController = navController)
            }


//            FloatingActionButton(onClick = {navController.navigate(Screen.CreateScreen.withArgs())}) {
//
//            }


        })
        {

            Text(text = "second page")
        }
}