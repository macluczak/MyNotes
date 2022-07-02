package com.macluczak.mywallet

import android.widget.EditText
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.MyWalletTheme

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueNote)
    ){
        Column {

            Calendar()

            CardViewHome()

        }

    }
}

@Composable
fun Calendar() {
    var month = "September"
    var days = listOf(4,5,6,7,8,9,10)
    var daysName = listOf("Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun")

    Column(modifier = Modifier
        .fillMaxWidth()
        ) {

        Text(

            style = MaterialTheme.typography.h3,
            text = month,
            color = Color.White

        )

        CalendarEntity(month = month, days = days, daysName = daysName)




    }
}


@Composable
fun CardViewHome(){

    Card(modifier = Modifier
        .fillMaxSize()
        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp))
        .background(Color.White),
        elevation = 7.dp
    ){

        Column(modifier = Modifier.padding(vertical = 15.dp)) {

            SearchView()

            Box(modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Blue)
            )

        }




    }


}


@Composable
fun SearchView(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 15.dp)){

        Box(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(BlueNote)
        ){

            var mText by remember { mutableStateOf("") }
            TextField(value = mText ,
                onValueChange = {mText = it},
                label = {Text("Search...")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            )

        }



    }

}


