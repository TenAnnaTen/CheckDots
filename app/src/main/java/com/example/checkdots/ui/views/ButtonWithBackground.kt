package com.example.checkdots.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkdots.R

@Composable
internal fun ButtonWithBackground(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(colorResource(id = R.color.main_yellow), RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.main_yellow))
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = colorResource(id = R.color.black),
        )
    }
}