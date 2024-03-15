package com.example.checkdots.Reg.NavScreenReg

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.checkdots.MainList.NavScreen.ScreenRoute
import com.example.checkdots.R
import com.example.checkdots.ui.theme.CheckDotsTheme

@Composable
fun ScreenMainReg(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.dscr),
            modifier = Modifier.size(500.dp)
        )
        ButtonWithBackground(
            text = stringResource(id = R.string.LogIn),
            onClick = { navController.navigate(ScreenRoute.AUTHORIZATION.name)},
            modifier = Modifier.padding(23.dp)
        )
        ButtonWithBackground(
            text = stringResource(id = R.string.LogOut),
            onClick = { navController.navigate(ScreenRoute.REGISTRATION.name) },
            modifier = Modifier.padding(horizontal = 23.dp)
        )
    }
}

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

@Composable
fun ScreenReg(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var inputName by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        EditField(
            label = R.string.name,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done),
            visualTransformation = VisualTransformation.None,
            value = inputName,
            onValueChange = {inputName = it}
        )
        EditField(
            label = R.string.password,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            value = inputPassword,
            onValueChange = {inputPassword = it}
        )
        Spacer(
            modifier = modifier
            .height(50.dp)
        )
        ButtonWithBackground(
            text = stringResource(id = R.string.LogOut),
            onClick = { navController.navigate(ScreenRoute.SCREENMAINLIST.name) },
            modifier = Modifier.padding(23.dp)
        )
    }
}

@Composable
internal fun EditField(
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

@Composable
fun ScreenAut(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var inputName by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        EditField(
            label = R.string.name,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done),
            visualTransformation = VisualTransformation.None,
            value = inputName,
            onValueChange = {inputName = it}
        )
        EditField(
            label = R.string.password,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            visualTransformation = PasswordVisualTransformation(),
            value = inputPassword,
            onValueChange = {inputPassword = it }
        )
        Spacer(
            modifier = modifier
                .height(50.dp)
        )
        ButtonWithBackground(
            text = stringResource(id = R.string.LogIn),
            onClick = {navController.navigate(ScreenRoute.SCREENMAINLIST.name) },
            modifier = Modifier.padding(23.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CheckDotsTheme {

    }
}