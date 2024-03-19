package com.example.checkdots.ui.navigation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material.icons.filled.TransitEnterexit
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.checkdots.R
import com.example.checkdots.data.storage.AccountStorage
import com.example.checkdots.ui.account.dotsAdd.DotsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationMain(navController: NavController) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val accountStorage = AccountStorage()
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.exit))
    }, navigationIcon = {
        IconButton(onClick = {
            openAlertDialog.value = true
        }) {
            Icon(
                imageVector = Icons.Default.SubdirectoryArrowLeft, contentDescription = "Go out"
            )
        }
    })

    if (openAlertDialog.value) {
        AlertDialogExample(onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                accountStorage.saveUserId(0)
                navController.navigate(ScreenRoute.SCREENREGAUT.name)
            },
            dialogTitle = "Выход из приложения",
            dialogText = "Вы действительно хотите выйти?",
            icon = Icons.Default.TransitEnterexit
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationRefactoring(navController: NavController) {
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.exit))
    }, navigationIcon = {
        IconButton(onClick = {
            navController.navigate(ScreenRoute.SCREENMAINLIST.name)
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Go out"
            )
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationView(navController: NavController, viewModel: DotsViewModel) {

    var isFavorite by remember { mutableStateOf(false) } // Состояние для отслеживания заполненности иконки "Like"
    var isLiked by remember { mutableStateOf(false) } // Состояние для отслеживания действия "Like"

    val accountStorage = AccountStorage()

    TopAppBar(title = {
        Text(text = stringResource(id = R.string.exit))
    }, navigationIcon = {
        IconButton(onClick = {
            navController.navigate(ScreenRoute.SCREEN3.name)
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Go out"
            )
        }
    }, actions = {
        IconButton(onClick = {
            isFavorite = !isFavorite
            if (isFavorite) {

                viewModel.likeDots(accountStorage.getDotsId())
                isLiked = true
            } else {
                viewModel.dislikeDots(accountStorage.getDotsId())
                isLiked = false
            }
        }) {
            val icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
            Icon(
                imageVector = icon, contentDescription = if (isLiked) "Unlike" else "Like"
            )
        }
    })
}
