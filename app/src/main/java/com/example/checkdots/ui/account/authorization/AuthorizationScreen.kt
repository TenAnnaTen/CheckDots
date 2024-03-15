package com.example.checkdots.ui.account.authorization

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.checkdots.R
import com.example.checkdots.ui.theme.CheckDotsTheme
import com.example.checkdots.ui.views.ButtonWithBackground
import com.example.checkdots.ui.views.EditField

@Composable
fun AuthorizationScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthorizationViewModel
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
            onClick = viewModel::navigateToMainList,
            modifier = Modifier.padding(23.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CheckDotsTheme {
        val navController = rememberNavController()
        AuthorizationScreen(viewModel = AuthorizationViewModel(navController))
    }
}