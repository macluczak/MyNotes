package com.macluczak.mywallet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
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

fun HomeScreen(viewModel: MainViewModel, navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = HippieBlue)
    systemUiController.setNavigationBarColor(
        color = HippieBluelight
    )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(HippieBlue)
        ) {
            Column(modifier = Modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {

                Calendar(viewModel = viewModel)
                CardViewHome(viewModel = viewModel, navController = navController)


            }


        }
//    }
}


@Composable
fun Calendar(viewModel: MainViewModel) {
    var month = viewModel.nowMonth
    var days = viewModel.listOfDay
    Log.d("Days", days.toString())
    var daysName = viewModel.listofWeek

    Column(modifier = Modifier
        .fillMaxWidth()
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 15.dp),
            style = MaterialTheme.typography.h2,
            text = month,
            color = Color.White,
            fontWeight = FontWeight.SemiBold


        )

        CalendarEntity(month = month, days = days, daysName = daysName)


    }
}


@Composable
fun CardViewHome(viewModel: MainViewModel, navController: NavController) {

    val listNoteLiveData = viewModel.getAllNotes()
    val listNote by listNoteLiveData.observeAsState(initial = emptyList())

    val listTaskLiveData = viewModel.getAllTasks()
    val listTask by listTaskLiveData.observeAsState(initial = emptyList())

    var searchByWord = viewModel.searchWord.observeAsState()

    Card(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(25.dp, 0.dp, 0.dp, 0.dp))
        .background(HippieBlue50),
        backgroundColor = HippieBlue50,
        elevation = 7.dp
    ) {

        Column() {

            val editNoteText = remember { mutableStateOf("Edit") }


            if(viewModel.getNoteEdit().value == true){
                editNoteText.value = "Cancel"
            }

            Surface(elevation = 4.dp ,
                modifier = Modifier.padding(0.dp,0.dp,0.dp,0.dp),
                color = OysterBay){

                SearchView(viewModel = viewModel)
            }




            LazyColumn() {
                item{
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp, 20.dp, 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "My Notes",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h6,
                            color = BlackCurrant
                        )


                    }
                }



                item {
                    DisplayNotes(notes = listNote,
                        viewModel = viewModel,
                        navController = navController)
                }

                item {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 20.dp, 20.dp, 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Let's do the Tasks",
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h6,
                            color = BlackCurrant
                        )

                    }
                }

                    var tasksFiltered = listTask.filter {
                        it.title.contains(searchByWord.value ?: "")
                    }

                        items(tasksFiltered.size) { index ->
                            if (tasksFiltered.isNotEmpty()) {
                                TaskItem(item = tasksFiltered[index],
                                    viewModel = viewModel,
                                    navController = navController)
                            }

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


}



@Composable
fun SearchView(viewModel: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 10.dp, 20.dp, 10.dp)) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .background(Color.White)
        ) {

            var mText by remember { mutableStateOf("") }

            TextField(value = mText,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.TopCenter),
                onValueChange = {
                    mText = it
                    viewModel.searchWord.value = it
                },
                label = { Text(modifier = Modifier.align(Alignment.CenterStart), text="Search") },


                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = HippieBluelight,
                    focusedIndicatorColor = HippieBluelight,
                    unfocusedIndicatorColor = HippieBluelight,
                    unfocusedLabelColor = HippieBlue,
                    focusedLabelColor = OysterBay,
                    cursorColor = BlackCurrant,
                    textColor = BlackCurrant),
                trailingIcon = {
                    if(true){
                        IconButton(onClick = {null}) {
                            Icon(modifier = Modifier .padding(0.dp, 0.dp, 20.dp, 0.dp), painter = painterResource(id = R.drawable.ic_baseline_search_24),
                                contentDescription = "search",
                            tint = HippieBlue)

                        }
                    }

                }

            )

        }


    }

}

@Preview()
@Composable
fun HomePreview() {
    MyWalletTheme {




    }
}

