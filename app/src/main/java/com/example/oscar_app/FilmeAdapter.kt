package com.example.oscar_app.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oscar_app.DetalhesFilme
import com.example.oscar_app.R
import com.example.oscar_app.models.Filme

class FilmeAdapter(
    private val filmes: List<Filme>,
    private val onFilmeClick: (Filme) -> Unit
) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val genreTextView: TextView = itemView.findViewById(R.id.genreTextView)

        fun bind(filme: Filme) {
            posterImageView.setImageResource(filme.posterPath)
            nameTextView.text = filme.name
            genreTextView.text = filme.genre
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetalhesFilme::class.java)
                intent.putExtra("filme", filme)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        holder.bind(filmes[position])
    }

    override fun getItemCount() = filmes.size
}
