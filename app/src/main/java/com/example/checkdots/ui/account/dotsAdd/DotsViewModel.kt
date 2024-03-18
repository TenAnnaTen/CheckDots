package com.example.checkdots.ui.account.dotsAdd

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.repository.DotsRepository
import com.example.checkdots.ui.navigation.ScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DotsViewModel(
    private val navController: NavController
) : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val dotsRepository = DotsRepository()

    var dotsListResponse: List<Dots> by mutableStateOf(listOf())
    var errorMessages: String by mutableStateOf("")

    fun addDots(dots: Dots) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = dotsRepository.registerDots(dots)
                if (response.isSuccessful) {
                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                } else {
                    _sharedFlow.emit("Ошибка добавления")
                }
            } catch (e: Exception) {
//                Log.d("MyLog", e.toString())
                _sharedFlow.emit("Ошибка сети")
            }
        }
    }

    fun getDotsList() {
        viewModelScope.launch {
            try {
                val dotsList = dotsRepository.getDots()
                dotsListResponse = dotsList
            } catch (e: Exception){
                Log.d("MyLog", e.toString())
                errorMessages = e.message.toString()
            }
        }
    }
}