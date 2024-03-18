package com.example.checkdots.data

import com.example.checkdots.data.service.AccountService
import com.example.checkdots.data.service.DotsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.1.46:8080/api/v1/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val accountService: AccountService by lazy {
        retrofit.create(AccountService::class.java)
    }
    val dotsService: DotsService by lazy {
        retrofit.create(DotsService::class.java)
    }
}