package com.example.oscar_app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oscar_app.adapters.FilmeAdapter
import com.example.oscar_app.models.Filme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VotarFilme : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_votar_filme)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)

        carregarFilmes()
    }

    private fun carregarFilmes() {
        // Exibe a ProgressBar
        progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {

            val filmes = listOf(
                Filme(1, "Piratas do Caribe", "Aventura", R.drawable.piratas),
                Filme(2, "Passageiros", "Ficção", R.drawable.passageiros),
                Filme(3, "La La Land", "Musical", R.drawable.land),
                Filme(4, "Avengers", "Aventura", R.drawable.avengers)
            )


            val adapter = FilmeAdapter(filmes) { filme ->
                println("Filme selecionado: ${filme.name}")
            }

            recyclerView.adapter = adapter
            progressBar.visibility = View.GONE
        }
    }
}
