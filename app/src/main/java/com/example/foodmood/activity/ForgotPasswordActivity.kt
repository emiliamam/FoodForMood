package com.example.foodmood.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.R

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var reqPasswordButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_refresh)
        reqPasswordButton = findViewById(R.id.req_password_button)
        reqPasswordButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordEmailActivity::class.java)
            startActivity(intent)
        }
    }
}