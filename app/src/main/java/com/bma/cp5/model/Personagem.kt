package com.bma.cp5.model

import android.media.Image
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Parcelize
data class Personagem(
    val id: Int = 0,
    val nome: String,
    val nomeReal: String,
    val hv: String,
    val poderes: String,
    val motivacao: String,
    val curiosidade: String,
    val foto: String?
) : Parcelable