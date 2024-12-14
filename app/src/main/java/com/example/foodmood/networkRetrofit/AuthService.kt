package com.example.foodmood.networkRetrofit

import com.example.foodmood.data.LoginRequest
import com.example.foodmood.data.LoginResponse
import com.example.foodmood.data.RegisterRequest
import com.example.foodmood.data.RegisterResponse
import com.example.foodmood.data.SendCodeResponse
import com.example.foodmood.model.Restaurant
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
     fun loginUser(
         @Query("email") email: String,
         @Query("password") password: String
     ): Call<LoginResponse>
    @POST("/auth/send_code")
    fun sendСode(
        @Query("email") email: String,
    ): Call<SendCodeResponse>
    interface ApiService {
        @GET("restaurant")
        suspend fun getRestaurants(
            @Query("page") page: Int,
            @Query("page_size") pageSize: Int
        ): RestaurantResponse

        @GET("/filter_restaurant")
        suspend fun filterRestaurants(
            @Query("page") page: Int = 1,
            @Query("page_size") pageSize: Int = 20,
            @Query("average_check") averageCheck: String? = null,
            @Query("kitchen") kitchen: String? = null,
            @Query("estimation") estimation: String? = null
        ): RestaurantResponse
    }



}

data class RestaurantResponse(
    val page: Int,
    val page_size: Int,
    val total_pages: Int,
    val total_items: Int,
    val data: List<Restaurant>
)