package com.example.checkdots.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.checkdots.R
import com.example.checkdots.data.model.Dots

@Composable
fun DotsItem(dots: Dots) {
    Card (
        modifier = Modifier
            .padding(10.dp)
    ){
        Row {
            DotsImage(dots)
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dots.heading, style = typography.headlineLarge)
                Text(text = dots.description, style = typography.bodyMedium)
            }
        }
    }
}

@Composable
private fun DotsImage(dots: Dots){
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
    )
}