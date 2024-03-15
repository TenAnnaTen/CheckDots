package com.example.checkdots.MainList.NavScreen

import androidx.compose.runtime.MutableState
import java.io.File
import java.util.concurrent.ExecutorService

object DataHolder {
    lateinit var outputDirectory: File
    lateinit var cameraExecutor: ExecutorService
    lateinit var shouldShowCamera: MutableState<Boolean>
}