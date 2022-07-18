package com.macluczak.mywallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.macluczak.mywallet.bottom_menu.BottomMenu
import com.macluczak.mywallet.bottom_menu.BottomMenuContent
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.data.task.Task
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
                        BottomMenuContent("List", "second_page", R.drawable.ic_baseline_list_24),
                        BottomMenuContent("Notes", "third_page", R.drawable.ic_baseline_widgets_24)
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

            var tasks = viewModel.getAllTasks().observeAsState().value
            val type = remember {
                mutableStateOf(3)
            }
            val taskfiltered = when(type.value){
                0 -> tasks?.filter { it.state == 0  }
                1 -> tasks?.filter { it.state == 1  }
                2 -> tasks?.filter { it.state == 2  }
                else -> tasks
            }





            Column() {

                Text(text = "Your Tasks")
                chooseTaskState(unCheck = true){
                    type.value = it

                }

               Text(text = Task.taskState[type.value])
                LazyColumn(){
                    items(taskfiltered!!.size){ index ->

                        if (taskfiltered.isNotEmpty()) {
                            TaskItem(item = taskfiltered[index],
                                viewModel = viewModel,
                                navController = navController)

                        }
                    }
                }
            }




        }
}