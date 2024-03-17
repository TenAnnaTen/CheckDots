package com.example.checkdots.data.repository

import com.example.checkdots.data.RetrofitInstance
import com.example.checkdots.data.model.Dots
import com.example.checkdots.data.model.ServerResponse
import com.example.checkdots.data.model.ServerResponseDots
import com.example.checkdots.data.model.User
import retrofit2.Response

class AccountRepository {

    suspend fun registerUser(user: User): Response<ServerResponse> {
        return RetrofitInstance.accountService.registerUser(user)
    }

    suspend fun authUser(user: User): Response<ServerResponse> {
        return RetrofitInstance.accountService.authUser(user)
    }
}