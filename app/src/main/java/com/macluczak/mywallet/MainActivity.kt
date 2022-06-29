package com.macluczak.mywallet

import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.macluczak.mywallet.data.Note
import com.macluczak.mywallet.ui.theme.MyWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWalletTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {


                    Greeting("Wallet")

                }
            }
        }
    }
}
@Composable
fun ListItem(item: Note){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)
        .height(60.dp)
        .background(color = Color.Gray)
    ){
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
        )
        {
            Text( modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(CenterVertically),
                text = item.title,
                fontSize = 16.sp)
        }
        Text( modifier = Modifier
            .padding(horizontal = 16.dp)
            .align(CenterEnd),
            text = item.message,
            fontSize = 16.sp)
    }
}

@Composable
fun Greeting(name: String) {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = CenterHorizontally ) {

        Text(text = "Hello $name!",
            color = Color.Red,
            textAlign = TextAlign.Center

        )



    }

}

@Composable
fun DisplayList(notes: List<Int>) {
    LazyColumn{

        }


        }



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyWalletTheme {
        ListItem(item = Note("note", "msg", 1,1))
        Greeting("Android")
    }
}