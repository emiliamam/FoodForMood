package com.example.foodmood.activity

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.MainRestaurantActivity
import com.example.foodmood.R

class MainActivity : AppCompatActivity() {

    private lateinit var registerButton: Button
    private lateinit var noRegisterButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_mood)
        registerButton = findViewById(R.id.login_button)
        noRegisterButton = findViewById(R.id.login_without_register_button)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        noRegisterButton.setOnClickListener {
            val intent = Intent(this, MainRestaurantActivity::class.java)
            startActivity(intent)
        }
    }
}