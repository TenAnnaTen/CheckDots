package com.example.checkdots.ui.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.checkdots.data.model.Dots

@Composable
fun ListWithDots(dotsList: List<Dots>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
//        items(
//            items = dots,
//            itemContent = {
//                DotsItem(dots = it)
//            }
//        )
        itemsIndexed(items = dotsList){index, item ->  
            DotsItem(dots = item)
        }
    }
}