package com.example.checkdots.data.service

import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponse
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DotsService {

    @POST("claim/create/{id}")
    suspend fun registerDots(@Body request: Dots, @Path("id") id: Int): Response<ServerResponseDots>

    @GET("claim/getall/{id}")
    suspend fun getDots(@Path("id") id: Int): List<Dots>

    @GET("claim/getall")
    suspend fun getPlanetDots(): List<Dots>

    @GET("claim/get/{id}")
    suspend fun getDotsWithId(@Path("id") id: Int): Response<ServerResponseDots>

    @DELETE("claim/delete/{id}")
    suspend fun delDots(@Path("id") id: Int)

    @PATCH("claim/uprating/{id}")
    suspend fun like(@Path("id") id: Int)

    @PATCH("claim/downrating/{id}")
    suspend fun dislike(@Path("id") id: Int)

    @PUT("claim/update/{id}")
    suspend fun refactorDots(@Body request: Dots, @Path("id") id: Int)
}