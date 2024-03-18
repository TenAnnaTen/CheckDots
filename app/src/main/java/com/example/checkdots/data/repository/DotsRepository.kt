package com.example.checkdots.data.repository

import android.util.Log
import com.example.checkdots.data.RetrofitInstance
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.storage.AccountStorage
import retrofit2.Response

class DotsRepository {

    private val accountStorage = AccountStorage()

    suspend fun registerDots(dots: Dots): Response<ServerResponseDots> {
//        Log.d("MyLog", accountStorage.getUserId().toString())

        val userId = accountStorage.getUserId()
        return RetrofitInstance.dotsService.registerDots(dots, userId)
    }
}