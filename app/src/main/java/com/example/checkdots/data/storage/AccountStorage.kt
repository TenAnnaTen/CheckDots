package com.example.checkdots.data.storage

import android.app.Application
import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountStorage: KoinComponent {

    private val context: Application by inject()

//    fun isUserLoggedIn(): Boolean {
//        val userId = getUserId(context)
//        return !userId.isNullOrEmpty()
//    }

    fun saveUserId(userId: Int) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("user_id", userId)
        editor.apply()
    }

    fun getUserId(): Int {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("user_id", 0)
    }
}