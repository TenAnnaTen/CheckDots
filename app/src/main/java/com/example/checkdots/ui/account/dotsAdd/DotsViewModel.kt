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
import com.example.checkdots.data.storage.AccountStorage
import com.example.checkdots.ui.account.authorization.AuthorizationState
import com.example.checkdots.ui.navigation.ScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DotsViewModel(
    private val navController: NavController
) : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _state = MutableStateFlow(DotsState())
    val state = _state.asStateFlow()

    private val dotsRepository = DotsRepository()
    private val accountStorage = AccountStorage()

    var dotsListResponse: List<Dots> by mutableStateOf(listOf())
    var errorMessages: String by mutableStateOf("")

    fun addDots(dots: Dots) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = dotsRepository.registerDots(dots)
                if (response.isSuccessful) {
                    val dotsId = response.body()?.claimId
                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                    _state.update { it.copy(dotsId = dotsId) }
                    if (dotsId != null) accountStorage.saveDotsId(dotsId)
                } else {
                    _sharedFlow.emit("Ошибка добавления")
                }
            } catch (e: Exception) {
                _sharedFlow.emit("Ошибка сети")
            }
        }
    }

    fun getDotsList() {
        viewModelScope.launch {
            try {
                val dotsList = dotsRepository.getDots()
                dotsListResponse = dotsList
            } catch (e: Exception) {
                Log.d("MyLog", e.toString())
                errorMessages = e.message.toString()
            }
        }
    }

    fun getPlanetDotsList() {
        viewModelScope.launch {
            try {
                val dotsList = dotsRepository.getPlanetDots()
                dotsListResponse = dotsList
            } catch (e: Exception) {
                Log.d("MyLog", e.toString())
                errorMessages = e.message.toString()
            }
        }
    }

    fun getDotsWithId(dotsId: Int) {
        viewModelScope.launch {
            try {
                val response = dotsRepository.getDotWithId(dotsId)
                Log.d("MyLog", dotsId.toString())
            } catch (e: Exception) {
                _sharedFlow.emit("Ошибка сети")
            }
        }
    }
}