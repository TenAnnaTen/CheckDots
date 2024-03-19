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

    suspend fun getDots(): List<Dots> {
        val userId = accountStorage.getUserId()
        return RetrofitInstance.dotsService.getDots(userId)
    }

    suspend fun getPlanetDots(): List<Dots> {
        return RetrofitInstance.dotsService.getPlanetDots()
    }

    suspend fun getDotWithId(dotsId: Int): Response<ServerResponseDots> {
        return RetrofitInstance.dotsService.getDotsWithId(dotsId)
    }

    suspend fun delDot(dotsId: Int) {
        return RetrofitInstance.dotsService.delDots(dotsId)
    }

    suspend fun likeDot(dotsId: Int){
        return RetrofitInstance.dotsService.like(dotsId)
    }
    suspend fun dislikeDot(dotsId: Int){
        return RetrofitInstance.dotsService.dislike(dotsId)
    }
    suspend fun refactorDot(dots: Dots, dotsId: Int){
        return RetrofitInstance.dotsService.refactorDots(dots, dotsId)
    }

}