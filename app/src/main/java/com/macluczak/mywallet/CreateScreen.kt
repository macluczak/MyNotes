package com.macluczak.mywallet


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build

import android.widget.DatePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.core.graphics.convertTo
import androidx.core.graphics.toColor
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.data.task.Task
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateScreen(viewModel: MainViewModel, navController: NavController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = HippieBlue50)
    systemUiController.setNavigationBarColor(color = HippieBlue100)

    var headerString = ""

    val createType = remember {
        mutableStateOf(0)
    }

    val labelTitleColor = remember {
        mutableStateOf(Color.Black)
    }

    val labelMessageColor = remember {
        mutableStateOf(Color.Black)
    }

    var createTitle by remember {
        mutableStateOf("")
    }
    var createDescription by remember {
        mutableStateOf("")
    }
    var startDate by remember {
        mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            .toString())
    }
    var endDate by remember {
        mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
            .toString())
    }
    var startTime by remember {
        mutableStateOf("12:00")
    }
    var endTime by remember {
        mutableStateOf("12:00")
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





    BoxWithConstraints(modifier = Modifier.background(HippieBlue50))
    {

        val height = constraints.maxHeight
        val width = constraints.maxWidth

        CanvasBackground(height = height, width = width)


        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
        ) {

            Text(text = "Create ${headerString}",
                style = MaterialTheme.typography.h6,
                color= BlackCurrant,
                modifier = Modifier
                    .padding(20.dp, 30.dp, 10.dp, 0.dp))

            ChooseType() {
                createType.value = it
            }

            if (createType.value == 0) {

                chooseTaskState() {
                    taskState.value = it
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp))


                Card(modifier = Modifier
                    .padding(20.dp, 10.dp, 20.dp, 80.dp)
                    .fillMaxSize()
                    .weight(4f)
                   , backgroundColor = HippieBlue50,
                            elevation = 7.dp) {


                    if(Build.VERSION.SDK_INT >= 32) {
                        BoxWithConstraints(modifier = Modifier
                            .fillMaxSize()
                            .blur(26.dp)) {
                            val height = constraints.maxHeight
                            val width = constraints.maxWidth
                            CanvasGlass(height = height, width = width)

                        }
                    }

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 0.dp, 10.dp)) {
                        TextField(value = createTitle,
                            onValueChange = {
                                createTitle = it
                                if (labelTitleColor.value != BlackCurrant) {
                                    labelTitleColor.value = BlackCurrant
                                }

                            },
                            maxLines = 1,
                            singleLine = true,


                            label = { Text("Title", style = MaterialTheme.typography.body1,  color = BlackCurrant) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = HippieBlue,
                                unfocusedIndicatorColor = HippieBlue300,
                                unfocusedLabelColor = labelTitleColor.value,
                                focusedLabelColor = labelTitleColor.value,
                                cursorColor = BlackCurrant,
                                textColor = BlackCurrant),


                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)


                        )

                        TextField(value = createDescription,
                            onValueChange = {
                                createDescription = it
                                if (labelMessageColor.value != BlackCurrant) {
                                    labelMessageColor.value = BlackCurrant
                                }

                            },
                            maxLines = 8,


                            label = { Text("Description", style = MaterialTheme.typography.body1, color = BlackCurrant) },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = HippieBlue,
                                unfocusedIndicatorColor = HippieBlue300,
                                unfocusedLabelColor = labelMessageColor.value,
                                focusedLabelColor = labelMessageColor.value,
                                cursorColor = BlackCurrant,
                                textColor = BlackCurrant),


                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize()
                                .weight(3f)
                                .padding(15.dp, 0.dp, 15.dp, 0.dp)


                        )

                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize()
                            .weight(3f)
                        ) {
                            Row(modifier = Modifier
                                .padding(15.dp, 10.dp, 15.dp, 10.dp)
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "Whole Day", color = BlackCurrant,  style = MaterialTheme.typography.body1)
                                Switch(checked = dayCheckBox.value, onCheckedChange = {
                                    dayCheckBox.value = it
                                },
                                    enabled = true, colors = SwitchDefaults.colors(
                                        checkedThumbColor = Coral
                                    )
//
                                )
                            }
                            Row(modifier = Modifier
                                .padding(15.dp, 10.dp, 15.dp, 10.dp)
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "Start", color =BlackCurrant,  style = MaterialTheme.typography.body1)
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
                                .padding(15.dp, 10.dp, 15.dp, 0.dp)
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = "End", color =BlackCurrant,  style = MaterialTheme.typography.body1)
                                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                                    ShowDatePicker(context = LocalContext.current) {
                                        endDate = it
                                    }
                                    if (!dayCheckBox.value) {
                                        ShowTimePicker(context = LocalContext.current,
                                            initHour = 12,
                                            initMinute = 0) {
                                            endTime = it
                                        }
                                    }


                                }

                            }




                        }



                    }

                }

//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxSize()
//                        .weight(2f)
//                        .padding(20.dp, 5.dp, 20.dp, 5.dp)
//
//
//                        .clip(RoundedCornerShape(25.dp))
//                        .background(HippieBlue200)
//
//                ) {
//                    Box(modifier = Modifier
//                        .padding(1.dp, 1.dp, 1.dp, 1.dp)
//
//                        .clip(RoundedCornerShape(25.dp))
//                        .align(Alignment.Center)
//                        .background(HippieBlue50))
//                    {
//
//                    Column(modifier = Modifier
//                        .fillMaxWidth()
//                        ) {
//                        Row(modifier = Modifier
//                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
//                            .fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween) {
//                            Text(text = "Whole Day", color = BlackCurrant)
//                            Switch(checked = dayCheckBox.value, onCheckedChange = {
//                                dayCheckBox.value = it
//                            },
//                                enabled = true, colors = SwitchDefaults.colors(
//                                    checkedThumbColor = Coral
//                                )
////
//                            )
//                        }
//                        Row(modifier = Modifier
//                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
//                            .fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween) {
//                            Text(text = "End", color =BlackCurrant)
//                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
//                                ShowDatePicker(context = LocalContext.current) {
//                                    endDate = it
//                                }
//                                if (!dayCheckBox.value) {
//                                    ShowTimePicker(context = LocalContext.current,
//                                        initHour = 12,
//                                        initMinute = 0) {
//                                        endTime = it
//                                    }
//                                }
//
//
//                            }
//
//                        }
//                        Row(modifier = Modifier
//                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
//                            .fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween) {
//                            Text(text = "Start", color =BlackCurrant)
//                            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
//                                ShowDatePicker(context = LocalContext.current) {
//                                    startDate = it
//
//                                }
//                                if (!dayCheckBox.value) {
//                                    ShowTimePicker(context = LocalContext.current,
//                                        initHour = 12,
//                                        initMinute = 0) {
//                                        startTime = it
//                                    }
//                                }
//
//
//                            }
//
//                        }
//
//
//
//
//                    }
//
//                }
//            }

                }
            if (createType.value == 1) {

                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 0.dp, 55.dp)
                ) {
                    ColorPicker(colorChoose.value) {
                        colorChoose.value = it
                    }


                    Box(modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 15.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Note.colorOfNote[colorChoose.value])) {

                        Column() {
                            TextField(value = createTitle,
                                onValueChange = {
                                    createTitle = it
                                    if (labelTitleColor.value != Color.Black) {
                                        labelTitleColor.value = Color.Black
                                    }

                                },
                                maxLines = 1,
                                singleLine = true,


                                label = { Text("Title", style = MaterialTheme.typography.body1) },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = BlueNote,
                                    unfocusedIndicatorColor = Color.LightGray,
                                    unfocusedLabelColor = labelTitleColor.value,
                                    focusedLabelColor = labelTitleColor.value,
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
                                    if (labelMessageColor.value != Color.Black) {
                                        labelMessageColor.value = Color.Black
                                    }

                                },
                                maxLines = 8,


                                label = { Text("Note") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = BlueNote,
                                    unfocusedIndicatorColor = Color.LightGray,
                                    unfocusedLabelColor = labelMessageColor.value,
                                    focusedLabelColor = labelMessageColor.value,
                                    cursorColor = Color.Black,
                                    textColor = Color.Black),


                                modifier = Modifier

//                                    .height(450.dp)
                                    .fillMaxSize()
                                    .padding(15.dp, 0.dp, 15.dp, 15.dp)


                            )


                        }

                    }

                }


            }


        }


        Surface( shape = CircleShape, color = Coral,
            elevation = 7.dp, modifier =
            Modifier
                .fillMaxWidth()
                .padding(20.dp, 15.dp, 20.dp, 20.dp)
                .align(Alignment.BottomEnd)
                .clickable {
                    if (createType.value == 0) {

                        if (createTitle.isBlank() && createDescription.isBlank()) {
                            labelTitleColor.value = Color.Red
                            labelMessageColor.value = Color.Red
                        } else if (createDescription.isBlank()) {
                            labelMessageColor.value = Color.Red
                        } else if (createTitle.isBlank()) {
                            labelTitleColor.value = Color.Red

                        } else {
                            viewModel.insertTask(Task(createTitle,
                                createDescription,
                                taskState.value,
                                startDate,
                                endDate,
                                startTime,
                                endTime,
                                dayCheckBox.value))
                            navController.popBackStack()
                        }
                    }
                    if (createType.value == 1) {
                        if (!viewModel.validateTitle(createTitle) && !viewModel.validateDescription(
                                createDescription)
                        ) {
                            labelTitleColor.value = Color.Red
                            labelMessageColor.value = Color.Red
                        } else if (!viewModel.validateDescription(createDescription)) {
                            labelMessageColor.value = Color.Red
                        } else if (!viewModel.validateTitle(createTitle)) {
                            labelTitleColor.value = Color.Red

                        } else {
                            viewModel.insertNote(Note(createTitle,
                                createDescription,
                                colorChoose.value))
                            navController.popBackStack()
                        }
                    }
                }


        ) {
            Box(Modifier.align(Alignment.Center)) {
                Text(text = "Create", color = Color.White , style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .align(Alignment.Center))

            }
        }


    }


}

@Composable
fun ColorPicker(colorChoose: Int = 0, onClick: (Int) -> Unit) {

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
                .border(2.dp,
                    color = if (selectIndexColor.value == it) Color.White else Color.Black,
                    RoundedCornerShape(25.dp))
                .background(Note.colorOfNote[it])) {

                Text("   ",
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
        colors = ButtonDefaults.buttonColors(
            backgroundColor = HippieBlue

        ),
        onClick = {
            mDatePickerDialog.show()
        },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
    ) {
        Text(text = "${mDate.value}", color = OysterBay)
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
    Button(colors = ButtonDefaults.buttonColors(
        backgroundColor = HippieBlue

    ),onClick = {
        timePickerDialog.show()
    }) {
        Text(text = "${time.value}", color = OysterBay)
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
                .padding(20.dp, 10.dp, 0.dp, 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    selectIndexType.value = it
                    onClick(it)
                }
                .background(if (selectIndexType.value == it) HippieBlue else HippieBlue50)) {

                Text(text = checkType[it],
                    color = if (selectIndexType.value == it) Color.White else HippieBlue,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp))

            }
        }
    }

}


@Composable
fun chooseTaskState(state: Int = 0, unCheck: Boolean = false, onClick: (Int) -> Unit) {
    val checkType = listOf("To-Do", "In Progress", "Done")

    val initVal = if (unCheck) 3 else state
    val selectIndexType = remember {
        mutableStateOf(initVal)
    }
    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically) {
        items(checkType.size) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .background(if (selectIndexType.value == 0 && selectIndexType.value == it) BlueNoteLight
                    else if (selectIndexType.value == 1 && selectIndexType.value == it) CoralLight
                    else if (selectIndexType.value == 2 && selectIndexType.value == it) GreenNote
                    else Color.Transparent)
                    .clickable {
                        if (selectIndexType.value == it && unCheck) {
                            selectIndexType.value = 3
                            onClick(selectIndexType.value)

                        } else {
                            selectIndexType.value = it
                            onClick(it)
                        }
                    }

                    .padding(10.dp, 5.dp, 10.dp, 5.dp)


            ) {

                Text(text = checkType[it],
                    color = if (selectIndexType.value == 0 && selectIndexType.value == it) BlueNote
                    else if (selectIndexType.value == 1 && selectIndexType.value == it) Coral
                    else if (selectIndexType.value == 2 && selectIndexType.value == it) GreenDone
                    else HippieBlue,
                    style = MaterialTheme.typography.body1)

            }
        }
    }

}

@Preview
@Composable
fun CreateScreenPreview(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp, 20.dp, 5.dp)
            .height(130.dp)

            .clip(RoundedCornerShape(25.dp))
            .background(HippieBlue200)

    ) {
        Box(modifier = Modifier
            .padding(1.dp, 1.dp, 1.dp, 1.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(25.dp))
            .align(Alignment.Center)
            .background(HippieBlue50))
        {

            Column(modifier = Modifier
                .fillMaxWidth()) {
                Row(modifier = Modifier
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Whole Day", color = BlackCurrant)
                    Switch(checked = true, colors = SwitchDefaults.colors(
                        checkedThumbColor = Coral
                    ), onCheckedChange = {

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
                    Text(text = "End", color =BlackCurrant)
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        ShowDatePicker(context = LocalContext.current) {

                        }
                        if (true) {
                            ShowTimePicker(context = LocalContext.current,
                                initHour = 12,
                                initMinute = 0) {

                            }
                        }


                    }

                }
                Row(modifier = Modifier
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Start", color =BlackCurrant)
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        ShowDatePicker(context = LocalContext.current) {


                        }
                        if (true) {
                            ShowTimePicker(context = LocalContext.current,
                                initHour = 12,
                                initMinute = 0) {

                            }
                        }


                    }

                }

                Row(modifier = Modifier
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
                    .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "End", color =BlackCurrant)
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        ShowDatePicker(context = LocalContext.current) {

                        }
                        if (true) {
                            ShowTimePicker(context = LocalContext.current,
                                initHour = 12,
                                initMinute = 0) {

                            }
                        }


                    }

                }


            }

        }
    }

}
