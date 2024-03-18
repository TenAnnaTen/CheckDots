package com.example.checkdots.data.service

import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponse
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface DotsService {

    @POST("claim/create/{id}")
    suspend fun registerDots(@Body request: Dots, @Path("id") id: Int): Response<ServerResponseDots>

}