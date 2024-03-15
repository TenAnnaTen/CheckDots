package com.example.checkdots.ui.navigation

import com.example.checkdots.R

sealed class BottomNavigationItem(
    val iconId: Int,
    val route: String
){
    object Screen1: BottomNavigationItem(R.drawable.list, ScreenRoute.SCREENMAINLIST.name)
    object Screen2: BottomNavigationItem(R.drawable.baseline_add_24, ScreenRoute.SCREEN2.name)
    object Screen3: BottomNavigationItem(R.drawable.planet, ScreenRoute.SCREEN3.name)
}
