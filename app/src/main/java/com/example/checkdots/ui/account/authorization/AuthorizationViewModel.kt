package com.example.checkdots.ui.account.authorization

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.checkdots.ui.navigation.ScreenRoute

class AuthorizationViewModel(
    private val navController: NavController
) : ViewModel() {

    fun navigateToMainList() {
        navController.navigate(ScreenRoute.SCREENMAINLIST.name)
    }
}