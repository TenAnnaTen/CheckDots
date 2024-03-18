package com.example.checkdots.ui.account.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.checkdots.data.model.User
import com.example.checkdots.data.repository.AccountRepository
import com.example.checkdots.data.storage.AccountStorage
import com.example.checkdots.ui.navigation.ScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val navController: NavController
) : ViewModel() {

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val _state = MutableStateFlow(AuthorizationState())
    val state = _state.asStateFlow()

    private val accountRepository = AccountRepository()
    private val accountStorage = AccountStorage()

    fun authUser(user: User) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = accountRepository.authUser(user)
                if (response.isSuccessful) {

                    val userId = response.body()?.userId

                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                    _state.update { it.copy(userId = userId) }
                    if (userId != null)  accountStorage.saveUserId(userId)
                } else {
                    _sharedFlow.emit("Ошибка входа")
                }
            } catch (e: Exception) {
                _sharedFlow.emit("Ошибка сети")
            }
        }
    }
}