package com.example.foodmood.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.MainRestaurantActivity
import com.example.foodmood.R

class ForgotPasswordEmailActivity : AppCompatActivity() {

    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email_confirmation)
        continueButton = findViewById(R.id.continue_button)
        continueButton.setOnClickListener {
            val intent = Intent(this, MainRestaurantActivity::class.java)
            startActivity(intent)
        }
    }
}