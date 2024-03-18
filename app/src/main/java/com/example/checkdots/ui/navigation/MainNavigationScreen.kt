package com.example.checkdots.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.checkdots.ui.Screen2
import com.example.checkdots.ui.Screen3
import com.example.checkdots.ui.ScreenMainList
import com.example.checkdots.ui.account.authorization.AuthorizationScreen
import com.example.checkdots.ui.account.authorization.AuthorizationViewModel
import com.example.checkdots.ui.account.dotsAdd.DotsViewModel
import com.example.checkdots.ui.account.registration.RegistrationScreen
import com.example.checkdots.ui.account.registration.RegistrationViewModel
import com.example.checkdots.ui.account.welcome.WelcomeScreen
import java.io.File
import java.util.concurrent.ExecutorService

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    outputDirectory: File,
    cameraExecutor: ExecutorService,
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    val registrationViewModel = RegistrationViewModel(navController = navController)
    val authorizationViewModel = AuthorizationViewModel(navController = navController)
    val dotsViewModel = DotsViewModel(navController = navController)

    Scaffold(bottomBar = {
        if (currentRoute != ScreenRoute.SCREENREGAUT.name &&
            currentRoute != ScreenRoute.REGISTRATION.name &&
            currentRoute != ScreenRoute.AUTHORIZATION.name) {
            BottomNavigation(navController = navController)
        }
    }) {
        NavHost(navController = navController, startDestination = ScreenRoute.SCREENREGAUT.name) {
            composable(ScreenRoute.SCREENMAINLIST.name) {
                ScreenMainList()
            }
            composable(ScreenRoute.SCREEN2.name) {
                Screen2(
                    navController = navController,
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    viewModel = dotsViewModel
                )
            }

            composable(ScreenRoute.SCREEN3.name) {
                Screen3()
            }
            composable(ScreenRoute.SCREENREGAUT.name) {
                WelcomeScreen(navController)
            }
            composable(ScreenRoute.REGISTRATION.name) {
                RegistrationScreen(viewModel = registrationViewModel)
            }
            composable(ScreenRoute.AUTHORIZATION.name) {
                AuthorizationScreen(viewModel = authorizationViewModel)
            }
        }
    }
}
