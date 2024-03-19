package com.example.checkdots.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.checkdots.R
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.storage.AccountStorage
import com.example.checkdots.ui.account.authorization.AuthorizationState
import com.example.checkdots.ui.account.dotsAdd.DotsState
import com.example.checkdots.ui.navigation.ScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Composable
fun DotsItem2(dots: Dots, navController: NavController, itemId: Int) {
    Card (
        modifier = Modifier
            .padding(10.dp)
    ){
        Row (
            Modifier.clickable{
                navController.navigate("${ScreenRoute.SCREENVIEW.name}/${itemId}")
                Log.d("MyLog", itemId.toString())
            }
        ) {
            DotsImage(dots)
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = dots.claimId.toString(), style = typography.headlineLarge)
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