package com.macluczak.mywallet

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.BlueNoteDark
import com.macluczak.mywallet.ui.theme.BlueNoteLight
import com.macluczak.mywallet.viewmodels.MainViewModel


@Composable

fun NoteDetails(id: Int, viewModel: MainViewModel){
    val systemUiController = rememberSystemUiController()

    val note = viewModel.getAllNotes().observeAsState().value?.singleOrNull{it.id == id}
    val mContext = LocalContext.current


    if(note != null) {

        

        val colorOfNote = remember {
            mutableStateOf(note?.color)
        }
        val selectAsFav = remember {
            mutableStateOf(note?.fav)
        }

        val title = remember{
            mutableStateOf(note?.title)
        }

        val message = remember{
            mutableStateOf(note?.message)
        }

        systemUiController.setSystemBarsColor(Note.colorOfNote[colorOfNote.value])


        



            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Note.colorOfNote[colorOfNote.value])
            ) {


                Column() {


                    TextField(value = title.value, onValueChange = { title.value = it },
                        maxLines = 1,
                        singleLine = true,

                        label = { Text(" ") },
                        textStyle = MaterialTheme.typography.h4,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black,
                            textColor = Color.Black),

                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(0.dp, 0.dp, 15.dp, 0.dp))
                    Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                        ColorPicker(colorChoose = colorOfNote.value) {

                            colorOfNote.value = it

                        }
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)) {

                            Box(modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .background(Color.Transparent)
                                .clip(RoundedCornerShape(25.dp))
                            ) {
                                Icon(painter = painterResource(id = if (selectAsFav.value) R.drawable.ic_baseline_favorite_24
                                else R.drawable.ic_baseline_favorite_uncheck_24),
                                    contentDescription = "fav", modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            selectAsFav.value = !selectAsFav.value
                                            val newNote = note.copy(fav = selectAsFav.value)
                                            viewModel.updateNote(newNote)
                                        })
                            }
                        }

                    }

                    TextField(value = message.value, onValueChange = {
                        message.value = it
                    },

                        textStyle = MaterialTheme.typography.body1,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black,
                            textColor = Color.Black),

                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp, 0.dp, 15.dp, 0.dp))

//                    (title.value != note.title || message.value != note.message || colorOfNote.value != note.color)


                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 0.dp, 15.dp, 30.dp)) {
                    AnimatedVisibility(
                        visible = title.value != note.title || message.value != note.message || colorOfNote.value != note.color,
                        modifier = Modifier.align(Alignment.BottomEnd),
                        enter = slideIn(tween(100, easing = LinearOutSlowInEasing)) { fullSize ->
                            IntOffset(fullSize.width / 4, 100)
                        } + fadeIn(
                            animationSpec = tween(durationMillis = 200)),
                        exit = slideOut(tween(100, easing = FastOutSlowInEasing)) {
                            IntOffset(-180, 50)
                        } + fadeOut(),
                    ) {
                        FloatingActionButton(onClick = {
                            if(title.value.isNotBlank() && message.value.isNotBlank()) {
                                val newNote = note.copy(title = title.value,
                                    message = message.value,
                                    color = colorOfNote.value
                                )
                                viewModel.updateNote(newNote)
                            }

                            else mToast(mContext)
                                                       },
                            backgroundColor = Color.Black,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.BottomEnd)) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_save_24),
                                tint = Color.White,
                                contentDescription = "save")
                        }

                    }

                }


            }

        }
    
}

