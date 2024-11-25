package com.example.foodmood.networkRetrofit

import com.example.foodmood.data.LoginRequest
import com.example.foodmood.data.LoginResponse
import com.example.foodmood.data.RegisterRequest
import com.example.foodmood.data.RegisterResponse
import com.example.foodmood.model.RestaurantResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("/auth/register")
    fun registerUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<RegisterResponse>
     @POST("/auth/login")
     fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("restaurant")
    suspend fun getRestaurants(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): RestaurantResponse


}
