package com.bma.cp5.model

data class Personagem(
    val id: Int = 0,
    val nome: String,
    val nomeReal: String,
    val hv: String,
    val poderes: String,
    val motivacao: String,
    val curiosidade: String
)