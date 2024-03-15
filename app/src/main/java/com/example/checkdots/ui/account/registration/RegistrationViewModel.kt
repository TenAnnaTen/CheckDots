package com.example.checkdots.ui.account.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.checkdots.ui.navigation.ScreenRoute
import com.example.checkdots.data.model.User
import com.example.checkdots.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val navController: NavController
): ViewModel() {

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    private val accountRepository = AccountRepository()

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = accountRepository.registerUser(user)
                if (response.isSuccessful) {
                    navController.navigate(ScreenRoute.SCREENMAINLIST.name)
                } else {
                    _sharedFlow.emit("Ошибка регистрации")
                }
            } catch (e: Exception) {
                _sharedFlow.emit("Ошибка сети")
            }
        }
    }
}