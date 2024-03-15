package com.example.checkdots.MainList.DotsList

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.checkdots.MainList.DotsList.DataProvider
import com.example.checkdots.MainList.DotsList.DotsListItem

@Composable
fun ListWithDots() {
    val dots = remember { DataProvider.dotsList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        items(
            items = dots,
            itemContent = {
                DotsListItem(dots = it)
            }
        )
    }
}