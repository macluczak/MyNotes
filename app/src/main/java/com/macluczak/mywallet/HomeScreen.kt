package com.macluczak.mywallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macluczak.mywallet.ui.theme.BlueNote
import com.macluczak.mywallet.ui.theme.MyWalletTheme

@Composable
fun homeScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlueNote)
    ){
        Column {

            calendar()

        }

    }
}

@Composable
fun calendar() {
    var month = "September"
    var days = listOf(4,5,6,7,8,9,10)
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {

        Text(
            style = MaterialTheme.typography.h2,
            text = month
        )

        Row(modifier = Modifier.fillMaxWidth()) {

        }

    }
}
