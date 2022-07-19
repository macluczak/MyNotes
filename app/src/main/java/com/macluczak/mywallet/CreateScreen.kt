package com.macluczak.mywallet


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context

import android.widget.DatePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp
import androidx.core.graphics.convertTo
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateScreen(viewModel: MainViewModel, navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.White)
    systemUiController.setNavigationBarColor(color = Color.White)

    var headerString = ""

    val createType = remember {
        mutableStateOf(0)
    }
    var createTitle by remember {
        mutableStateOf("")
    }
    var createDescription by remember {
        mutableStateOf("")
    }
    var startDate by remember {
        mutableStateOf("")
    }
    var endDate by remember {
        mutableStateOf("")
    }
    var startTime by remember {
        mutableStateOf("")
    }
    val endTime by remember {
        mutableStateOf("")
    }
    val taskState = remember {
        mutableStateOf(0)
    }
    val dayCheckBox = remember {
        mutableStateOf(false)
    }
    val colorChoose = remember {
        mutableStateOf(0)
    }

    when (createType.value) {
        0 -> headerString = "Task"
        1 -> headerString = "Note"
    }




//    Scaffold(modifier = Modifier.fillMaxSize(),
//
//        bottomBar = {
//            Box(Modifier
//                .fillMaxWidth()
//                .padding(15.dp)
//                .clip(RoundedCornerShape(10.dp))
//                .clickable {
//                    if (createType.value == 0) {
//                        viewModel.insertTask(Task(createTitle, createDescription, taskState.value,
//                            startDate, endDate, startTime, endTime, dayCheckBox.value))
//                        navController.popBackStack()
//                    }
//                    if (createType.value == 1) {
//                        viewModel.insertNote(Note(createTitle,
//                            createDescription,
//                            colorChoose.value))
//                        navController.popBackStack()
//                    }
//                }
//                .background(BlueNote)) {
//                Text(text = "Create", color = Color.White,
//                    modifier = Modifier
//                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
//                        .align(Alignment.Center))
//
//            }
//        }
//
//
//    ) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 55.dp)
        ) {

            Text(text = "Create ${headerString}",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(15.dp, 0.dp, 10.dp, 0.dp))

            ChooseType() {
                createType.value = it
            }

            if (createType.value == 0) {

                chooseTaskState() {
                    taskState.value = it
                }


                Box(modifier = Modifier
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueNoteLight)) {

                    Column() {
                        TextField(value = createTitle,
                            onValueChange = {
                                createTitle = it

                            },
                            maxLines = 1,
                            singleLine = true,


                            label = { Text("Title") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = BlueNoteLight,
                                focusedIndicatorColor = BlueNote,
                                unfocusedIndicatorColor = Color.LightGray,
                                cursorColor = Color.Black,
                                textColor = Color.Black),


                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)


                        )

                        TextField(value = createDescription,
                            onValueChange = {
                                createDescription = it

                            },
                            maxLines = 8,


                            label = { Text("Description") },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = BlueNoteLight,
                                focusedIndicatorColor = BlueNote,
                                unfocusedIndicatorColor = Color.LightGray,
                                cursorColor = Color.Black,
                                textColor = Color.Black),


                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)


                        )


                    }

                }

                Box(modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(BlueNoteLight)) {

                    Column(modifier = Modifier
                        .fillMaxWidth()) {
                        Row(modifier = Modifier
                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Whole Day")
                            Switch(checked = dayCheckBox.value, onCheckedChange = {
                                dayCheckBox.value = it
                            },
                                enabled = true
//                            colors = SwitchDefaults.colors()
                            )
                        }
                        Row(modifier = Modifier
                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Start")
                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                ShowDatePicker(context = LocalContext.current) {
                                    startDate = it

                                }
                                if (!dayCheckBox.value) {
                                    ShowTimePicker(context = LocalContext.current,
                                        initHour = 12,
                                        initMinute = 0) {
                                        startTime = it
                                    }
                                }


                            }

                        }

                        Row(modifier = Modifier
                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "End")
                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                ShowDatePicker(context = LocalContext.current) {
                                    endDate = it
                                }
                                if (!dayCheckBox.value) {
                                    ShowTimePicker(context = LocalContext.current,
                                        initHour = 12,
                                        initMinute = 0) {
                                        endDate = it
                                    }
                                }


                            }

                        }


                    }

                }
            }
            if (createType.value == 1) {

                Column(modifier = Modifier.fillMaxSize()) {
                    ColorPicker(colorChoose.value) {
                        colorChoose.value = it
                    }


                    Box(modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Note.colorOfNote[colorChoose.value])) {

                        Column() {
                            TextField(value = createTitle,
                                onValueChange = {
                                    createTitle = it

                                },
                                maxLines = 1,
                                singleLine = true,


                                label = { Text("Title") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = BlueNote,
                                    unfocusedIndicatorColor = Color.LightGray,
                                    cursorColor = Color.Black,
                                    textColor = Color.Black),


                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .padding(15.dp, 0.dp, 15.dp, 0.dp)
                            )

                            TextField(value = createDescription,
                                onValueChange = {
                                    createDescription = it

                                },
                                maxLines = 8,


                                label = { Text("Note") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = BlueNote,
                                    unfocusedIndicatorColor = Color.LightGray,
                                    cursorColor = Color.Black,
                                    textColor = Color.Black),


                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(190.dp)
                                    .padding(15.dp, 0.dp, 15.dp, 0.dp)


                            )


                        }

                    }

                }


            }


        }

//    }

}

@Composable
fun ColorPicker(colorChoose: Int = 0, onClick: (Int) -> Unit) {
//    val colors = listOf(YellowNote, BlueNote, PurpleNote, GreenNote)
    val selectIndexColor = remember {
        mutableStateOf(colorChoose)
    }



    LazyRow() {
        items(Note.colorOfNote.size) {


            Box(modifier = Modifier
                .padding(15.dp, 10.dp, 0.dp, 10.dp)
                .clip(RoundedCornerShape(25.dp))
                .clickable {
                    selectIndexColor.value = it
                    onClick(it)
                }
                .border(2.dp, color =  if (selectIndexColor.value == it) Color.White else Color.Black, RoundedCornerShape(25.dp))
                .background(Note.colorOfNote[it])) {

                Text( "   ",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp))

            }

        }

    }

}

@Composable
fun ShowDatePicker(context: Context, onSet: (String) -> Unit) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val formatter = SimpleDateFormat("dd/MM/yyyy")


    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember {
        mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            .toString())
    }

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = SimpleDateFormat("dd/MM/yyyy",
                Locale.getDefault()).format(formatter.parse("$mDayOfMonth/${mMonth + 1}/$mYear")!!)
            onSet(mDate.value)
        }, mYear, mMonth, mDay
    )

    Button(
        onClick = {
            mDatePickerDialog.show()
        },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
        Text(text = "${mDate.value}", color = Color.White)
    }

}


@Composable
fun ShowTimePicker(context: Context, initHour: Int, initMinute: Int, onSet: (String) -> Unit) {
    val time = remember { mutableStateOf("12:00") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            when (minute) {
                0 -> time.value = "$hour:00"
                else -> time.value = "$hour:$minute"
            }
            onSet(time.value)

        }, initHour, initMinute, false
    )
    Button(onClick = {
        timePickerDialog.show()
    }) {
        Text(text = "${time.value}")
    }
}


@Composable
fun ChooseType(onClick: (Int) -> Unit) {
    val checkType = listOf("Task", "Note")
    val selectIndexType = remember {
        mutableStateOf(0)
    }
    LazyRow() {
        items(checkType.size) {
            Box(modifier = Modifier
                .padding(15.dp, 10.dp, 0.dp, 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    selectIndexType.value = it
                    onClick(it)
                }
                .background(if (selectIndexType.value == it) BlueNote else Color.White)) {

                Text(text = checkType[it],
                    color = if (selectIndexType.value == it) Color.White else BlueNote,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp))

            }
        }
    }

}


@Composable
fun chooseTaskState(unCheck: Boolean = false, onClick: (Int) -> Unit) {
    val checkType = listOf("to do", "in progress", "done")
    val initVal = if(unCheck) 3 else 0
    val selectIndexType = remember {
        mutableStateOf(initVal)
    }
    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
        items(checkType.size) {
            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        if (selectIndexType.value == it && unCheck) {
                            selectIndexType.value = 3
                            onClick( selectIndexType.value)

                        } else {
                            selectIndexType.value = it
                            onClick(it)
                        }
                    }
                    .background(if (selectIndexType.value == it) BlueNote else Color.White),
            ) {

                Text(text = checkType[it],
                    color = if (selectIndexType.value == it) Color.White else BlueNote,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp))

            }
        }
    }

}