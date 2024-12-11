package com.example.oscar_app.api

data class VotoRequest(
    val usuario: String,
    val token: Int,
    val filmeId: Int,
    val diretorId: Int
)