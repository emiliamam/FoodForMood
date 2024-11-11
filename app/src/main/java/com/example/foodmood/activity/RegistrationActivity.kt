package com.example.foodmood.activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.R
import com.example.foodmood.data.RegisterResponse
import com.example.foodmood.networkRetrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var nameInput: EditText
    private lateinit var registerButton: Button
    private lateinit var loginBut: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        nameInput = findViewById(R.id.name_input)
        registerButton = findViewById(R.id.login_button)
        loginBut = findViewById(R.id.register_here)

        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            registerUser(email, password)
        }
        loginBut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(email: String, password: String) {
        RetrofitClient.instance.registerUser(email, password).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RegistrationActivity, "Регистрация успешна", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@RegistrationActivity, "Ошибка регистрации: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@RegistrationActivity, "Ошибка соединения: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}