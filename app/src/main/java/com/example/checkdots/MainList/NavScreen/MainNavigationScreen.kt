package com.example.checkdots.MainList.NavScreen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.checkdots.MainList.BottomNavigation.BottomNavigation
import com.example.checkdots.Reg.NavScreenReg.ScreenAut
import com.example.checkdots.Reg.NavScreenReg.ScreenMainReg
import com.example.checkdots.Reg.NavScreenReg.ScreenReg
import java.io.File
import java.util.concurrent.ExecutorService

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainNavigationScreen(
    navController: NavHostController,
    outputDirectory: File,
    cameraExecutor: ExecutorService,
    context: Context
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

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
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    context = context
                )
            }

            composable(ScreenRoute.SCREEN3.name) {
                Screen3()
            }
            composable(ScreenRoute.SCREENREGAUT.name) {
                ScreenMainReg(navController)
            }
            composable(ScreenRoute.REGISTRATION.name) {
                ScreenReg(navController)
            }
            composable(ScreenRoute.AUTHORIZATION.name) {
                ScreenAut(navController)
            }
        }
    }
}
