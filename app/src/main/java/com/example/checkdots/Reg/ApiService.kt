package com.example.checkdots.Reg
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    suspend fun register(@Body request: RegistrationRequest): retrofit2.Response<RegistrationResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): retrofit2.Response<LoginResponse>
}

data class RegistrationRequest(
    val username: String,
    val password: String
)

data class RegistrationResponse(
    val success: Boolean,
    val message: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)

object RetrofitInstance {
    private const val BASE_URL = "https://your-api-url.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}