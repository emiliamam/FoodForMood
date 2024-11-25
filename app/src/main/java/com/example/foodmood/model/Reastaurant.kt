package com.example.foodmood.model

data class Restaurant(
    val name: String,
    val average_check: Int,
    val kitchen: String,
    val estimation: Double,
    val type: String
)

data class RestaurantResponse(
    val page: Int,
    val page_size: Int,
    val total_pages: Int,
    val total_items: Int,
    val data: List<Restaurant>
)
