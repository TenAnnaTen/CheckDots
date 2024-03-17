package com.example.checkdots.data.service

import com.example.checkdots.data.model.ServerResponse
import com.example.checkdots.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {

    @POST("user/create")
    suspend fun registerUser(@Body request: User): Response<ServerResponse>

    @POST("user/check")
    suspend fun authUser(@Body request: User): Response<ServerResponse>
}