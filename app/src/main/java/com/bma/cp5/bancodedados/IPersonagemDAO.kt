package com.bma.cp5.bancodedados

import com.bma.cp5.model.Personagem

interface IPersonagemDAO {
    fun salvar(personagem: Personagem): Boolean
    fun atualizar(personagem: Personagem): Boolean
    fun remover(personagem: String): Boolean
    fun listar(): List<Personagem>
}