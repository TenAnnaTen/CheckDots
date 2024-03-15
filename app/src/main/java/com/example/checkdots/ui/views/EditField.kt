package com.example.checkdots.ui.views

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EditField(
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
        .padding(15.dp)
        .width(350.dp)
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(label)) },
        visualTransformation =  visualTransformation,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}