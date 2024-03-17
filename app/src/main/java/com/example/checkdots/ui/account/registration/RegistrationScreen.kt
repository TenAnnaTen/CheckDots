package com.example.checkdots.ui.account.registration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.checkdots.R
import com.example.checkdots.data.model.User
import com.example.checkdots.ui.views.ButtonWithBackground
import com.example.checkdots.ui.views.EditField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel
) {

    val context = LocalContext.current

    var inputName by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.sharedFlow.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
        EditField(
            label = R.string.name,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            visualTransformation = VisualTransformation.None,
            value = inputName,
            onValueChange = { inputName = it }
        )
        EditField(
            label = R.string.password,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            value = inputPassword,
            onValueChange = { inputPassword = it }
        )
        Spacer(
            modifier = modifier
                .height(50.dp)
        )
        ButtonWithBackground(
            text = stringResource(id = R.string.LogOut),
            onClick = {
                viewModel.registerUser(
                    User(
                        name = inputName,
                        password = inputPassword
                    )
                )
            },
            modifier = Modifier.padding(23.dp)
        )
    }
}
