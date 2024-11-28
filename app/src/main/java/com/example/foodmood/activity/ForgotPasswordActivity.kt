package com.example.foodmood.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.foodmood.MainRestaurantActivity
import com.example.foodmood.R
import com.example.foodmood.data.LoginResponse
import com.example.foodmood.data.SendCodeResponse
import com.example.foodmood.networkRetrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var reqPasswordButton: Button
    private lateinit var emailInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_refresh)
        reqPasswordButton = findViewById(R.id.req_password_button)
        emailInput = findViewById(R.id.email_input)
        reqPasswordButton.setOnClickListener {
            sendCode(emailInput.text.toString());
        }
    }

    private fun sendCode(email: String) {
        RetrofitClient.instance.sendСode(email).enqueue(object :
            Callback<SendCodeResponse> {
            override fun onResponse(call: Call<SendCodeResponse>, response: Response<SendCodeResponse>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@ForgotPasswordActivity, ForgotPasswordEmailActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@ForgotPasswordActivity, "Код отправлен успешно", Toast.LENGTH_LONG).show()


                } else {
                    Toast.makeText(this@ForgotPasswordActivity, "Ошибка: ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SendCodeResponse>, t: Throwable) {
                Toast.makeText(this@ForgotPasswordActivity, "Ошибка соединения: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

}