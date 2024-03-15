package com.example.checkdots

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.checkdots.ui.camera.shouldShowCamera
import com.example.checkdots.utils.DataHolder
import com.example.checkdots.ui.navigation.MainNavigationScreen
import com.example.checkdots.ui.theme.CheckDotsTheme
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("MyTag", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("MyTag", "Permission denied")
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        outputDirectory = getOutputDirectory(context = this)
        cameraExecutor = Executors.newSingleThreadExecutor()

        DataHolder.outputDirectory = outputDirectory
        DataHolder.cameraExecutor = cameraExecutor
        DataHolder.shouldShowCamera = shouldShowCamera

        setContent {
            val navController = rememberNavController()
            CheckDotsTheme {
                MainNavigationScreen(
                    navController = navController,
                    outputDirectory = outputDirectory,
                    cameraExecutor = cameraExecutor,
                )
            }
        }

        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED  -> {
                Log.i("MyTag", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) && ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> Log.i("MyTag", "Show camera permissions dialog")

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
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

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
