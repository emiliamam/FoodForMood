package com.example.foodmood.activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.MainRestaurantActivity
import com.example.foodmood.R
import com.example.foodmood.data.LoginRequest
import com.example.foodmood.data.LoginResponse
import com.example.foodmood.networkRetrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: TextView
    private lateinit var registerHere: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        forgotPasswordButton = findViewById(R.id.forgot_password)
        registerHere = findViewById(R.id.register_here)
        loginButton.setOnClickListener {
            var isValid = true
            val email = emailInput.text.toString()
            if (email.isBlank() || email.isEmpty()) {
                emailInput.error = "Заполните поле"
                isValid = false
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.error = "Неверный формат email"
                isValid = false
            }
            val password = passwordInput.text.toString()
            if (password.isBlank() || password.isEmpty()) {
                passwordInput.error = "Заполните поле"
                isValid = false;
            } else if (password.length < 6) {
                passwordInput.error = "Пароль должен быть длиннее 6 символов"
                isValid = false
            }
            if (isValid) {
                loginUser(email, password)
            }
        }
        registerHere.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        forgotPasswordButton.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        RetrofitClient.instance.loginUser(email, password).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@LoginActivity, MainRestaurantActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@LoginActivity, "Авторизация успешна", Toast.LENGTH_LONG).show()
                    val token = response.body()?.access_token
                    token?.let {
                        saveAccessToken(it) // Сохраняем токен
                    }

                } else {
                    Toast.makeText(this@LoginActivity, "Ошибка авторизации: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Ошибка соединения: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun saveAccessToken(token: String) {
        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("access_token", token) // Сохраняем токен
        editor.apply()
    }


}

