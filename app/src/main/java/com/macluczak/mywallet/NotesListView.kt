package com.macluczak.mywallet

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import androidx.navigation.NavController
import com.macluczak.mywallet.navigation.Screen
import com.macluczak.mywallet.data.note.Note
import com.macluczak.mywallet.ui.theme.*
import com.macluczak.mywallet.viewmodels.MainViewModel


@Composable
fun NoteView(item: Note, viewModel: MainViewModel, navController: NavController, fullsize: Boolean= false) {

    BoxWithConstraints(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 10.dp, 30.dp, 0.dp)) {

        val width = maxWidth







        Surface(modifier = Modifier
            .clickable {
                navController.navigate(Screen.NoteDetail.withArgs(item.id))


            }
            .fillMaxWidth()
            .heightIn(min = width / 3), elevation = 7.dp, color =  Note.colorOfNote[item.color],
        shape = RoundedCornerShape(5.dp, 25.dp, 5.dp, 5.dp)) {



            Column(
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 15.dp)
                    .fillMaxWidth()


            )
            {
                Text(modifier = Modifier
                    .padding(15.dp, 0.dp, 25.dp, 0.dp),
                    text = item.title,
                    maxLines = 1,
                    softWrap = true,
                    style = MaterialTheme.typography.h6,
                    color = BlackCurrant
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(modifier = Modifier
                    .padding(15.dp, 0.dp, 25.dp, 0.dp),
                    text = item.message,
                    maxLines = 10,
                    softWrap = true,
                    style = MaterialTheme.typography.body1,
                    color = BlackCurrant)
            }




        }

        Card(modifier = Modifier
            .align(Alignment.TopEnd)
            .size(25.dp), elevation = 4.dp
            , shape = RoundedCornerShape(0.dp, 25.dp, 0.dp, 5.dp),
            backgroundColor = Color(
                ColorUtils.blendARGB(Note.colorOfNote[item.color].toArgb(), 0xFF0A0D25.toInt(), 0.1f)
            ) ){

        }
    }

}

@Composable
fun NoteItem(item: Note, viewModel: MainViewModel, navController: NavController, fullsize: Boolean= false) {

    Card(modifier = Modifier
        .padding(0.dp, 0.dp, 15.dp, 0.dp)
        , backgroundColor = Color.Transparent, elevation = 8.dp) {

        var visible = remember { mutableStateOf(false) }
        var color = remember { mutableStateOf(TransparentRed) }
        val editNoteLiveData = viewModel.getNoteEdit().observeAsState()

        BoxWithConstraints(modifier = Modifier

            .width(160.dp)
            .fillMaxWidth(if (fullsize) 1f else 0f)
            .aspectRatio(1f)
            .background(HippieBlue300)
            .clickable {
                navController.navigate(Screen.NoteDetail.withArgs(item.id))


            }

        ) {

            val height = constraints.maxHeight
            val width = constraints.maxWidth

            val mediumTonePoint1 = Offset(0f, height * 0.3f)
            val mediumTonePoint2 = Offset(width * 0.1f, height * 0.33f)
            val mediumTonePoint3 = Offset(width * 0.4f, height * 0.05f)
            val mediumTonePoint4 = Offset(width * 0.75f, height * 0.7f)
            val mediumTonePoint5 = Offset(width * 1.5f, -height.toFloat())

            val mediumTonePath = Path().apply {

                moveTo(mediumTonePoint1.x, mediumTonePoint1.y)

                calcFromTo(mediumTonePoint1, mediumTonePoint2)
                calcFromTo(mediumTonePoint2, mediumTonePoint3)
                calcFromTo(mediumTonePoint3, mediumTonePoint4)
                calcFromTo(mediumTonePoint4, mediumTonePoint5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()

            }

            val lightTonePoint1 = Offset(0f, height * 0.35f)
            val lightTonePoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightTonePoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightTonePoint4 = Offset(width * 0.65f, height.toFloat())
            val lightTonePoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

            val lightTonePath = Path().apply {

                moveTo(lightTonePoint1.x, lightTonePoint1.y)

                calcFromTo(lightTonePoint1, lightTonePoint2)
                calcFromTo(lightTonePoint2, lightTonePoint3)
                calcFromTo(lightTonePoint3, lightTonePoint4)
                calcFromTo(lightTonePoint4, lightTonePoint5)

                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()

            }

            Canvas(modifier = Modifier
                .fillMaxSize()
                .blur(if (editNoteLiveData.value == true) 1.dp else 0.dp)) {

                drawPath(path = mediumTonePath,
                    color = HippieBlue200)

                drawPath(path = lightTonePath,
                    color = HippieBlue100)


            }


            Row(
                modifier = Modifier
                    .padding(5.dp, 5.dp, 15.dp, 15.dp)
                    .fillMaxWidth()

            )
            {
                Text(modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .align(Alignment.CenterVertically),
                    text = item.title,
                    maxLines = 1,
                    softWrap = true,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Box(modifier = Modifier.padding(5.dp, 5.dp, 10.dp, 10.dp).align(Alignment.BottomEnd)) {
                Icon(painter = painterResource(id = if (item.fav) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_uncheck_24), tint = Color.White,
                    contentDescription = "fav", modifier = Modifier

                        .size(16.dp)
                )
            }

            if (editNoteLiveData.value == true) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color.value)
                    .clickable {
                        viewModel.deleteNote(item)
                    }
                ){
                    
                    Image(painter = painterResource(id = R.drawable.ic_baseline_delete_outline_24),
                        contentDescription = "Delete",
                    modifier = Modifier
                        .size(64.dp, 64.dp)
                        .align(Alignment.Center))

                    
                    
                }
            }
        }


    }


}

@Composable
fun DisplayNotes(notes: List<Note>, viewModel: MainViewModel, navController: NavController) {

    var searchByWord = viewModel.searchWord.observeAsState()

    var notesFiltered = notes.filter {
        it.title.contains(searchByWord.value?: "")
    }



    LazyRow(modifier = Modifier
        .padding(0.dp,0.dp, 0.dp, 0.dp)){

        item{
            Box(modifier = Modifier
                .height(160.dp)
                .width(20.dp)


            ){

            }
        }

        items(notesFiltered.size) { index ->

            if(notesFiltered.isNotEmpty()) {
                NoteItem(item = notesFiltered[index],
                    viewModel = viewModel,
                    navController = navController)
            }

        }
        item{
            Box(modifier = Modifier
                .height(160.dp)
                .width(5.dp)

                ){

            }
        }
    }


}