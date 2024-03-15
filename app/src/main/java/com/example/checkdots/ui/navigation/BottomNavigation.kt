package com.example.checkdots.ui.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.checkdots.R

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomNavigationItem.Screen1,
        BottomNavigationItem.Screen2,
        BottomNavigationItem.Screen3
    )
    NavigationBar(
        containerColor = colorResource(id = R.color.main_yellow)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                          navController.navigate(item.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(35.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = colorResource(id = R.color.black),
                    indicatorColor = colorResource(id = R.color.white)
                ),
            )
        }
    }
}