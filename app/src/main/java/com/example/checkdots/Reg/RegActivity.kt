package com.example.checkdots.Reg

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.checkdots.MainList.Camera.shouldShowCamera
import com.example.checkdots.MainList.NavScreen.MainNavigationScreen
import com.example.checkdots.R
import com.example.checkdots.ui.theme.CheckDotsTheme
import com.yandex.mapkit.MapKitFactory
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RegActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        outputDirectory = getOutputDirectory(context = this)
        cameraExecutor = Executors.newSingleThreadExecutor()

        setContent {
            val navController = rememberNavController()
            CheckDotsTheme {
                MainNavigationScreen(
                    navController = navController,
                    outputDirectory = outputDirectory,
                    cameraExecutor = cameraExecutor,
                    lifecycleScope = lifecycleScope,
                    context = this
                )
            }
        }
    }

    private fun getOutputDirectory(context: Context): File {
        val mediaDirs = context.getExternalFilesDirs(null)
        val mediaDir = mediaDirs.firstOrNull()?.let {
            File(it, context.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
    }
}
