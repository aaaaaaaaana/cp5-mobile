package com.bma.cp5.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Personagem(
    val id: Int = 0,
    val nome: String,
    val nomeReal: String,
    val hv: String,
    val poderes: String,
    val motivacao: String,
    val curiosidade: String
) : Parcelable