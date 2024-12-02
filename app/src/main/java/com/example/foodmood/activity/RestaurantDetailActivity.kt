package com.example.foodmood.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.R

class RestaurantDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        // Получаем данные из Intent
        val restaurantName = intent.getStringExtra("RESTAURANT_NAME")
        val restaurantDescription = intent.getStringExtra("RESTAURANT_DESCRIPTION")
        val restaurantKitchen = intent.getStringExtra("RESTAURANT_KITCHEN")
        val restaurantEstimation = intent.getFloatExtra("RESTAURANT_ESTIMATION", 0f)
        val restaurantType = intent.getStringExtra("RESTAURANT_TYPE")

        // Находим TextView по ID и выводим информацию
        val nameTextView: TextView = findViewById(R.id.detail_name)
        val descriptionTextView: TextView = findViewById(R.id.detail_description)
        val kitchenTextView: TextView = findViewById(R.id.detail_kitchen)
        val estimationTextView: TextView = findViewById(R.id.detail_estimation)
        val typeTextView: TextView = findViewById(R.id.detail_type)

        nameTextView.text = restaurantName
        descriptionTextView.text = restaurantDescription
        kitchenTextView.text = "Кухня: $restaurantKitchen"
        estimationTextView.text = "Оценка: $restaurantEstimation"
        typeTextView.text = "Тип: $restaurantType"
    }
}
