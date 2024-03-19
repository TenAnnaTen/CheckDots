package com.example.checkdots.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.checkdots.data.model.Dots

@Composable
fun ListWithDots2(dotsList: List<Dots>, navController: NavController) {
    LazyColumn(
    modifier = Modifier
    .padding(bottom = 80.dp, top = 55.dp),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        itemsIndexed(items = dotsList){index, item ->
            DotsItem2(dots = item, navController, item.claimId)
        }
    }
}