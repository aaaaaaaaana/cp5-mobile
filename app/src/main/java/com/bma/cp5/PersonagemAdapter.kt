package com.bma.cp5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bma.cp5.model.Personagem

class PersonagemAdapter(
    private var personagens: List<Personagem>,
    private val onItemClickListener: (Personagem) -> Unit
) : RecyclerView.Adapter<PersonagemAdapter.PersonagemViewHolder>() {

    class PersonagemViewHolder(itemView: View, private val onItemClickListener: (Personagem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nomePersonagem: TextView = itemView.findViewById(R.id.nomePersonagem)
        private val imageViewPersonagem: ImageView = itemView.findViewById(R.id.imageViewPersonagem)

        fun bind(personagem: Personagem) {
            nomePersonagem.text = personagem.nome

            imageViewPersonagem.setImageResource(R.drawable.batman)
            itemView.setOnClickListener { onItemClickListener(personagem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonagemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_personagem, parent, false)
        return PersonagemViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: PersonagemViewHolder, position: Int) {
        val personagem = personagens[position]
        holder.bind(personagem)
    }

    override fun getItemCount(): Int = personagens.size

    fun updatePersonagens(newPersonagens: List<Personagem>) {
        this.personagens = newPersonagens
        notifyDataSetChanged()
    }
}