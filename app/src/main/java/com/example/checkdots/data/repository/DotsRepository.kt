package com.example.checkdots.data.repository

import com.example.checkdots.data.RetrofitInstance
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.storage.AccountStorage
import retrofit2.Response

class DotsRepository {

    private val accountStorage = AccountStorage()

    suspend fun registerDots(dots: Dots): Response<ServerResponseDots> {

        val userId = accountStorage.getUserId()
        return RetrofitInstance.dotsService.registerDots(dots, userId)
    }

    suspend fun getDots(): List<Dots>{
        val userId = accountStorage.getUserId()
        return RetrofitInstance.dotsService.getDots(userId)
    }
}