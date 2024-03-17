package com.example.checkdots.ui

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.checkdots.ui.camera.CameraScreen
import com.example.checkdots.ui.camera.shouldShowCamera
import com.example.checkdots.ui.views.ListWithDots
import com.example.checkdots.R
import com.example.checkdots.ui.navigation.ScreenRoute
import com.example.checkdots.ui.theme.CheckDotsTheme
import com.example.checkdots.ui.views.ButtonWithBackground
import com.example.checkdots.ui.views.EditField
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun ScreenMainList() {
    ListWithDots()
}

@Composable
fun Screen2(
    navController: NavHostController,
    outputDirectory: File,
    executor: ExecutorService,
) {
    val capturedPhotoUri = remember { mutableStateOf<Uri?>(null) }
    var label by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(350.dp)
                .background(color = colorResource(id = R.color.black))
                .clickable {
                    shouldShowCamera.value = true
                },
        ) {
            if (shouldShowCamera.value) {
                CameraScreen(
                    outputDirectory = outputDirectory,
                    executor = executor,
                    onImageCaptured = { uri ->
                        capturedPhotoUri.value = uri
                        shouldShowCamera.value = false
                    },
                    onError = { Log.e("MyTag", "View error:", it) }
                )
            }else if (capturedPhotoUri.value != null) {
                Image(
                    painter = rememberImagePainter(capturedPhotoUri.value),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        SpaceBetween()
        EditField(
            label = R.string.label_dots,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            value = label,
            onValueChange = {label = it},
            modifier = Modifier
                .width(350.dp)
        )
        EditField(
            label = R.string.text_dots,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            value = text,
            onValueChange = {text = it},
            modifier = Modifier
                .width(350.dp)
                .padding(vertical = 20.dp)
        )
        EditField(
            label = R.string.location,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            visualTransformation = VisualTransformation.None,
            value = location,
            onValueChange = {location = it},
            modifier = Modifier
                .width(350.dp)
        )
        SpaceBetween()
        ButtonWithBackground(
            text = stringResource(id = R.string.btn_save),
            onClick = {

                navController.navigate(ScreenRoute.SCREENMAINLIST.name)
            }
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}



@Composable
fun Screen3() {
    ListWithDots()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckDotsTheme {
    }
}

@Composable
private fun SpaceBetween(){
    Spacer(modifier = Modifier.height(50.dp))
}