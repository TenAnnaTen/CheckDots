package com.example.checkdots.data.service

import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponse
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DotsService {

    @POST("claim/create")
    suspend fun registerDots(@Body request: Dots): Response<ServerResponseDots>

}