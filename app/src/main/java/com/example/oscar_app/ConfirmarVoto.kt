package com.example.oscar_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.oscar_app.api.ApiService
import com.example.oscar_app.api.VotoRequest
import com.example.oscar_app.votoData.VotoData
import kotlinx.coroutines.launch

class ConfirmarVoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar_voto)

        // Recuperar as preferências do usuário
        val sharedPrefs = getSharedPreferences("oscar_app", MODE_PRIVATE)
        val username = sharedPrefs.getString("username", null)
        val token = sharedPrefs.getInt("token", -1)

        // Exibir informações do voto
        val filmeView = findViewById<TextView>(R.id.Filme)
        val diretorView = findViewById<TextView>(R.id.diretortext)
        val confirmarButton = findViewById<Button>(R.id.confirmarButton)

        filmeView.text = VotoData.filmeName
        diretorView.text = VotoData.diretorName

        // Configurar ação do botão
        confirmarButton.setOnClickListener {
            lifecycleScope.launch {
                if (username != null && token != -1) {
                    registrarVoto(username, token)
                } else {
                    Toast.makeText(this@ConfirmarVoto, "Erro: Usuário não autenticado.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private suspend fun registrarVoto(username: String, token: Int) {
        val apiService = ApiService.create()
        val filmeId = VotoData.filmeId ?: -1
        val diretorId = VotoData.diretorId ?: -1

        val votoRequest = VotoRequest(
            usuario = username,
            token = token,
            filmeId = filmeId,
            diretorId = diretorId
        )

        try {
            val response = apiService.registrarVoto(votoRequest)
            if (response.isSuccessful) {
                Toast.makeText(this, "Voto registrado com sucesso!", Toast.LENGTH_SHORT).show()
                val sharedPrefs = getSharedPreferences("oscar_app", MODE_PRIVATE)
                sharedPrefs.edit().clear().apply()
                val loginIntent = Intent(this, MainActivity::class.java)
                loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(loginIntent)
                finish()
            } else {
                Toast.makeText(this, "Erro ao registrar voto: ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Falha na conexão: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
