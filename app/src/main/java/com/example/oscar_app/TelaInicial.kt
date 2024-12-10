package com.example.oscar_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.oscar_app.votoData.VotoData

class TelaInicial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_inicial)
        val btnsair = findViewById<Button>(R.id.sairButton)
        val btnfilm = findViewById<Button>(R.id.votarFilmeButton)
        val btndiretor = findViewById<Button>(R.id.votarDiretorButton)
        val btnconfirmar = findViewById<Button>(R.id.confirmarButton)
        println("Diretor:" + VotoData.diretorName)

        btnsair.setOnClickListener {
            finishAffinity()
        }

        btnfilm.setOnClickListener {
            val telaFilme = Intent(this, VotarFilme::class.java)
            startActivity(telaFilme);
        }

        btndiretor.setOnClickListener {
            val telaDiretor = Intent(this, VotarDiretor::class.java)
            startActivity(telaDiretor);
        }

        btnconfirmar.setOnClickListener {
            val telaConfirmar = Intent(this, ConfirmarVoto::class.java)
            startActivity(telaConfirmar);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}