package com.example.checkdots.ui

import android.net.Uri
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.checkdots.R
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.storage.AccountStorage
import com.example.checkdots.ui.account.dotsAdd.DotsViewModel
import com.example.checkdots.ui.navigation.AlertDialogExample
import com.example.checkdots.ui.navigation.ScreenRoute
import com.example.checkdots.ui.views.ButtonWithBackground
import com.example.checkdots.ui.views.EditField
import com.example.checkdots.ui.views.ListWithDots
import com.example.checkdots.ui.views.ListWithDots2
import com.example.checkdots.utils.Regexp
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun ScreenMainList(viewModel: DotsViewModel, navController: NavHostController) {
    ListWithDots(dotsList = viewModel.dotsListResponse, navController = navController)
    viewModel.getDotsList()
}

@Composable
fun Screen2(
    navController: NavHostController,
    outputDirectory: File,
    executor: ExecutorService,
    viewModel: DotsViewModel
) {
    val context = LocalContext.current
    val accountStorage = AccountStorage()

    LaunchedEffect(key1 = Unit) {
        viewModel.sharedFlow.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

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
        SpaceBetween()
        EditField(
            label = R.string.label_dots,
            placeholder = null,
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
            placeholder = null,
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
            placeholder = R.string.error_regex,
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
                if (Regexp(location)){
                    viewModel.addDots(
                        Dots(
                            claimId = accountStorage.getDotsId(),
                            heading = label,
                            description = text,
                            address = location,
                            path_image = ""
                        )
                    )
                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                } else Toast.makeText(context, "Местоположение введено неверно", Toast.LENGTH_SHORT).show()

            }
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}



@Composable
fun Screen3(viewModel: DotsViewModel, navController: NavHostController) {
    ListWithDots2(dotsList = viewModel.dotsListResponse, navController)
    viewModel.getPlanetDotsList()
}

@Composable
fun screenView(viewModel: DotsViewModel, dotsId: Int){

    val accountStorage = AccountStorage()
    viewModel.getDotsWithId(dotsId)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Заголовок: ${accountStorage.getDotsHeading()}", fontSize = 25.sp)
        SpaceBetween()
        Text(text = "Описание: ${accountStorage.getDotsDescription()}", fontSize = 25.sp)
        SpaceBetween()
        Text(text = "Местоположение: ${accountStorage.getDotsAddress()}", fontSize = 25.sp)
    }
}

@Composable
fun screenRefactoring(viewModel: DotsViewModel, navController: NavHostController, dotsId: Int){

    val accountStorage = AccountStorage()
    viewModel.getDotsWithId(dotsId)

    val openAlertDialog = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp),
        verticalArrangement = Arrangement.Center) {
        Text(text = "Заголовок: ${accountStorage.getDotsHeading()}", fontSize = 25.sp)
        SpaceBetween()
        Text(text = "Описание: ${accountStorage.getDotsDescription()}", fontSize = 25.sp)
        SpaceBetween()
        Text(text = "Местоположение: ${accountStorage.getDotsAddress()}", fontSize = 25.sp)
        SpaceBetween()
        ButtonWithBackground(text = stringResource(id = R.string.refactor), onClick = { navController.navigate("${ScreenRoute.REFACTOR.name}/${dotsId}") })
        SpaceBetween()
        ButtonWithBackground(
            text = stringResource(id = R.string.del),
            onClick = {
                openAlertDialog.value = true
            }
        )
    }
    if (openAlertDialog.value){
        AlertDialogExample(
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                viewModel.delDots(dotsId)
            },
            dialogTitle = stringResource(id = R.string.del),
            dialogText = stringResource(id = R.string.del_question),
            icon = Icons.Default.Delete
        )
    }
}

@Composable
fun refactoring(viewModel: DotsViewModel, navController: NavHostController, dotsId: Int){

    val accountStorage = AccountStorage()
    viewModel.getDotsWithId(dotsId)

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.sharedFlow.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
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
        SpaceBetween()
        EditField(
            label = R.string.label_dots,
            placeholder = null,
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
            placeholder = null,
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
            placeholder = R.string.error_regex,
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
                if (Regexp(location)){
                    viewModel.refactorDots(
                        Dots(
                            claimId = accountStorage.getDotsId(),
                            heading = label,
                            description = text,
                            address = location,
                            path_image = ""
                        ), accountStorage.getDotsId()
                    )
                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                } else Toast.makeText(context, "Местоположение введено неверно", Toast.LENGTH_SHORT).show()

            }
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}

@Composable
private fun SpaceBetween(){
    Spacer(modifier = Modifier.height(50.dp))
}