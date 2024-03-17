package com.example.checkdots.data.repository

import com.example.checkdots.data.RetrofitInstance
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponseDots
import retrofit2.Response

class DotsRepository {
    suspend fun registerDots(dots: Dots): Response<ServerResponseDots> {
        return RetrofitInstance.dotsService.registerDots(dots)
    }
}