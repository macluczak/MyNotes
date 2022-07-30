package com.macluczak.mywallet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.macluczak.mywallet.bottom_menu.BottomMenu
import com.macluczak.mywallet.bottom_menu.BottomMenuContent
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun SecondPage(viewModel: MainViewModel, navController: NavController){
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = HippieBlue100)
    systemUiController.setNavigationBarColor(
        color = HippieBluelight
    )

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


    Surface(modifier = Modifier.fillMaxSize(), color = HippieBlue50){

        Column() {

            Surface(modifier = Modifier,
                elevation = 4.dp, color = HippieBlue100) {

                Column(Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 10.dp)) {

                    Row(Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp), Arrangement.SpaceBetween){
                    Text(text = "My Tasks", style = MaterialTheme.typography.h6,
                        color = BlackCurrant,
                        modifier = Modifier
                            .padding(20.dp, 0.dp, 15.dp, 0.dp))
//                        Text(text = Task.taskState[type.value],
//                            style = MaterialTheme.typography.body1,
//                            color = BlackCurrant,
//                            modifier = Modifier
//                                .padding(20.dp, 0.dp, 20.dp, 0.dp))
                                }
                    chooseTaskState(unCheck = true) {
                        type.value = it

                    }

                }

            }




//            Text(text = Task.taskState[type.value],
//                style = MaterialTheme.typography.body1,
//                color = BlackCurrant,
//                modifier = Modifier
//                    .padding(20.dp, 10.dp, 10.dp, 0.dp))
            LazyColumn() {
                item{
                    Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
                }
                items(taskfiltered!!.size) { index ->

                    if (taskfiltered.isNotEmpty()) {
                        TaskItem(item = taskfiltered[index],
                            viewModel = viewModel,
                            navController = navController)

                    }
                }
            }
        }
    }



//        }
}