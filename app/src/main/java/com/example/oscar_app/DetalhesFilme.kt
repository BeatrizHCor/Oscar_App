package com.example.oscar_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oscar_app.models.Filme
import com.example.oscar_app.votoData.VotoData

class DetalhesFilme : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val posterImageView: ImageView = findViewById(R.id.detalhesPosterImageView)
        val nomeTextView: TextView = findViewById(R.id.detalhesNomeTextView)
        val generoTextView: TextView = findViewById(R.id.detalhesGeneroTextView)
        val votarButton: Button = findViewById(R.id.votarButton)
        val voltarButton: Button = findViewById(R.id.voltarButton)

        val filme = intent.getSerializableExtra("filme") as? Filme

        filme?.let {
            posterImageView.setImageResource(it.posterPath)
            nomeTextView.text = it.name
            generoTextView.text = it.genre
        }

        votarButton.setOnClickListener {
            filme?.let {
                VotoData.filmeId = it.id
                VotoData.filmeName = it.name
                println("Voto registrado localmente: ${it.name}")
                Toast.makeText(this, "Voto registrado com sucesso para: ${it.name}", Toast.LENGTH_SHORT).show()
                val telaInicialIntent = Intent(this, TelaInicial::class.java)
                startActivity(telaInicialIntent)
                finish()
            }
        }
    }
}
