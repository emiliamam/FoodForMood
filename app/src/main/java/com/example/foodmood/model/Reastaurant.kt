package com.example.foodmood.model

data class Restaurant(
    val name: String,
    val description: String,
    val average_check: Float,
    val kitchen: String,
    val estimation: Float,
    val type: String,
    var isFavorite: Boolean = false
)
