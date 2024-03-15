package com.example.checkdots.ui.account.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.checkdots.ui.navigation.ScreenRoute
import com.example.checkdots.R
import com.example.checkdots.ui.views.ButtonWithBackground

@Composable
fun WelcomeScreen(
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