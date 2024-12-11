package com.example.oscar_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.oscar_app.api.ApiService
import com.example.oscar_app.api.LoginRequest
import com.example.oscar_app.api.LoginResponse
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val apiService = ApiService.create()
    private lateinit var loginButton: Button
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.button)
        emailInput = findViewById(R.id.editTextTextEmailAddress)
        passwordInput = findViewById(R.id.editTextTextPassword)

        loginButton.setOnClickListener {
            lifecycleScope.launch {
                handleLogin()
            }
        }
    }

    private suspend fun handleLogin() {
        try {
            val username = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isBlank() || password.isBlank()) {
                showMessage("Preencha todos os campos")
                return
            }

            val response = apiService.login(LoginRequest(username, password))

            if (response.isSuccessful) {
                print("success")
                response.body()?.let { loginResponse: LoginResponse ->
                    // Save token and username for later use
                    getSharedPreferences("oscar_app", MODE_PRIVATE).edit().apply {
                        putInt("token", loginResponse.token)
                        putString("username", username)
                        apply()
                    }

                    val telaInicial = Intent(this, TelaInicial::class.java)
                    startActivity(telaInicial)
                    finish()
                }
            } else {
                showMessage("Erro no login: Credenciais inválidas")
            }
        } catch (e: Exception) {
            showMessage("Erro ao conectar com o servidor")
        }
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}