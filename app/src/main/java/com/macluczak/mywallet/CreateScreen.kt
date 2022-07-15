package com.macluczak.mywallet

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.inputmethodservice.Keyboard
import android.widget.DatePicker
import android.widget.RadioGroup
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.macluczak.mywallet.Calendar
import com.macluczak.mywallet.bottom_menu.fab
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.BlueNoteDark
import com.macluczak.mywallet.ui.theme.BlueNoteLight
import com.macluczak.mywallet.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CreateScreen(viewModel: MainViewModel, navController: NavController) {
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
    val taskState = remember {
        mutableStateOf(0)
    }
    val dayCheckBox = remember {
        mutableStateOf(false)
    }

    when (createType.value) {
        0 -> headerString = "Task"
        1 -> headerString = "Note"
    }



    Scaffold(modifier = Modifier.fillMaxSize(),

        bottomBar = {
            Box(Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable { }
                .background(BlueNote)) {
                Text(text = "Create", color = Color.White,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .align(Alignment.Center))

            }
        }


    ) {

        Column(modifier = Modifier.fillMaxSize()) {

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
            }

            if (createType.value == 0) {

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
            }

            if (createType.value == 0) {
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
                            Text(text = "Start of Task")
                            Row(){
                                ShowDatePicker(context = LocalContext.current)
                                ShowTimePicker(context = LocalContext.current, initHour = 12, initMinute = 0)

                            }

                        }

                        Row(modifier = Modifier
                            .padding(15.dp, 10.dp, 15.dp, 10.dp)
                            .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "End of Task")
                            Row(){
                                ShowDatePicker(context = LocalContext.current)
                                ShowTimePicker(context = LocalContext.current, initHour = 12, initMinute = 0)

                            }

                        }

                    }

                }
            }


        }

    }


}
@Composable
fun ShowDatePicker(context: Context) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()).toString()) }

    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

        Button(onClick = {
            mDatePickerDialog.show()
        },
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
        ) {
            Text(text = "${mDate.value}", color = Color.White)
        }

}


@Composable
fun ShowTimePicker(context: Context, initHour: Int, initMinute: Int) {
    val time = remember { mutableStateOf("12:00") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            when(minute){
                0 -> time.value = "$hour:00"
                else -> time.value = "$hour:$minute"
            }

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
fun chooseTaskState(onClick: (Int) -> Unit) {
    val checkType = listOf("to do", "in progress", "done")
    val selectIndexType = remember {
        mutableStateOf(0)
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
                        selectIndexType.value = it
                        onClick(it)
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