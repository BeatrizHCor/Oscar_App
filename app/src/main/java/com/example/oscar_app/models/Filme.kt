package com.example.oscar_app.models

import java.io.Serializable

data class Filme(
    val id: Int,
    val name: String,
    val genre: String,
    val posterPath: Int
) : Serializable
