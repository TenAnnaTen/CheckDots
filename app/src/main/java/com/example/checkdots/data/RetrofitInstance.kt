package com.example.checkdots.data

import com.example.checkdots.data.service.AccountService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://your-api-url.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val accountService: AccountService by lazy {
        retrofit.create(AccountService::class.java)
    }
}