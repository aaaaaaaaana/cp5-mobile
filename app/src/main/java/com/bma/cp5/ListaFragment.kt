package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bma.cp5.bancodedados.PersonagemDAO

class ListaFragment : Fragment() {
    private lateinit var personagemDAO: PersonagemDAO
    private lateinit var imageViewPersonagem1: ImageView
    private lateinit var imageViewPersonagem2: ImageView
    private lateinit var imageViewPersonagem3: ImageView
    private lateinit var imageViewPersonagem4: ImageView
    private lateinit var imageViewPersonagem5: ImageView
    private lateinit var imageViewPersonagem6: ImageView
    private lateinit var nomePersonagem1: TextView
    private lateinit var nomePersonagem2: TextView
    private lateinit var nomePersonagem3: TextView
    private lateinit var nomePersonagem4: TextView
    private lateinit var nomePersonagem5: TextView
    private lateinit var nomePersonagem6: TextView
    private lateinit var cardView1: View
    private lateinit var cardView2: View
    private lateinit var cardView3: View
    private lateinit var cardView4: View
    private lateinit var cardView5: View
    private lateinit var cardView6: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)


        personagemDAO = PersonagemDAO(requireContext())


        imageViewPersonagem1 = view.findViewById(R.id.imageViewPersonagem1)
        imageViewPersonagem2 = view.findViewById(R.id.imageViewPersonagem2)
        imageViewPersonagem3 = view.findViewById(R.id.imageViewPersonagem3)
        imageViewPersonagem4 = view.findViewById(R.id.imageViewPersonagem4)
        imageViewPersonagem5 = view.findViewById(R.id.imageViewPersonagem5)
        imageViewPersonagem6 = view.findViewById(R.id.imageViewPersonagem6)
        nomePersonagem1 = view.findViewById(R.id.nomePersonagem1)
        nomePersonagem2 = view.findViewById(R.id.nomePersonagem2)
        nomePersonagem3 = view.findViewById(R.id.nomePersonagem3)
        nomePersonagem4 = view.findViewById(R.id.nomePersonagem4)
        nomePersonagem5 = view.findViewById(R.id.nomePersonagem5)
        nomePersonagem6 = view.findViewById(R.id.nomePersonagem6)
        cardView1 = view.findViewById(R.id.cardView1)
        cardView2 = view.findViewById(R.id.cardView2)
        cardView3 = view.findViewById(R.id.cardView3)
        cardView4 = view.findViewById(R.id.cardView4)
        cardView5 = view.findViewById(R.id.cardView5)
        cardView6 = view.findViewById(R.id.cardView6)


        carregarPersonagens()


        cardView1.setOnClickListener {
            abrirPersonagem(0)
        }
        cardView2.setOnClickListener {
            abrirPersonagem(1)
        }
        cardView3.setOnClickListener {
            abrirPersonagem(2)
        }
        cardView4.setOnClickListener {
            abrirPersonagem(3)
        }
        cardView5.setOnClickListener {
            abrirPersonagem(4)
        }
        cardView6.setOnClickListener {
            abrirPersonagem(5)
        }

        return view
    }

    private fun carregarPersonagens() {
        val personagens = personagemDAO.listar()
        if (personagens.size >= 1) {
            nomePersonagem1.text = personagens[0].nome

        }
        if (personagens.size >= 2) {
            nomePersonagem2.text = personagens[1].nome

        }
        if (personagens.size >= 3) {
            nomePersonagem3.text = personagens[2].nome

        }
        if (personagens.size >= 4) {
            nomePersonagem4.text = personagens[3].nome

        }
        if (personagens.size >= 5) {
            nomePersonagem5.text = personagens[4].nome

        }
        if (personagens.size >= 6) {
            nomePersonagem6.text = personagens[5].nome

        }
    }


    private fun abrirPersonagem(posicao: Int) {
        val personagens = personagemDAO.listar()
        if (personagens.size > posicao) {
            val personagem = personagens[posicao]
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            val personagemFragment = PersonagemFragment.newInstance(PersonagemFragment(personagem))
            transaction.replace(R.id.fragmentContainerView, personagemFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}