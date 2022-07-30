package com.macluczak.mywallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.macluczak.mywallet.bottom_menu.BottomMenu
import com.macluczak.mywallet.bottom_menu.BottomMenuContent
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel

@Composable
fun ThirdPage(viewModel: MainViewModel, navController: NavController){

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = HippieBlue100)
    systemUiController.setNavigationBarColor(
        color = HippieBluelight
    )


        val notes = viewModel.getAllNotes().observeAsState().value


        Column(Modifier.background(HippieBlue50)) {

            Surface(modifier = Modifier,
                elevation = 4.dp, color = HippieBlue100) {

                Column(Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 10.dp)) {

                    Row(Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp), Arrangement.SpaceBetween){
                        Text(text = "My Notes", style = MaterialTheme.typography.h6,
                            color = BlackCurrant,
                            modifier = Modifier
                                .padding(20.dp, 0.dp, 15.dp, 0.dp))

                    }

                }

            }


            LazyColumn() {
                item{
                    Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
                }
                items(notes!!.size) { index ->

                    NoteView(item = notes[index],
                        viewModel = viewModel,
                        navController = navController,
                    fullsize = true)

                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                    ) {

                    }

                }
            }
        }


    }

