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

    fun saveDotsId(dotsId: Int) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("dots_id", dotsId)
        editor.apply()
    }

    fun getUserId(): Int {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("user_id", 0)
    }

    fun getDotsId(): Int {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getInt("dots_id", 0)
    }

    fun saveDotsHeading(dotsHeading: String) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("dots_heading", dotsHeading)
        editor.apply()
    }

    fun getDotsHeading(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("dots_heading", "")
    }
    fun saveDotsDescription(dotsDescription: String) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("dots_description", dotsDescription)
        editor.apply()
    }

    fun getDotsDescription(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("dots_description", "")
    }

    fun saveDotsAddress(dotsAddress: String) {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("dots_address", dotsAddress)
        editor.apply()
    }

    fun getDotsAddress(): String? {
        val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("dots_address", "")
    }
}