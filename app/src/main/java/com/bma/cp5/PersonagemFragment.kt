package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bma.cp5.model.Personagem

class PersonagemFragment : Fragment() {


    private lateinit var nomePersonagem: TextView
    private lateinit var nomeRealTextView: TextView
    private lateinit var heroiVilaoTextView: TextView
    private lateinit var poderesTextView: TextView
    private lateinit var motivacaoTextView: TextView
    private lateinit var curiosidadeTextView: TextView

    companion object {
        const val ARG_PERSONAGEM = "personagem"


        fun newInstance(personagem: Personagem): PersonagemFragment {
            val fragment = PersonagemFragment()
            val args = Bundle()
            args.putParcelable(ARG_PERSONAGEM, personagem)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personagem, container, false)

        nomePersonagem = view.findViewById(R.id.nomePersonagem)
        nomeRealTextView = view.findViewById(R.id.nomeReal)
        heroiVilaoTextView = view.findViewById(R.id.heroiVilao)
        poderesTextView = view.findViewById(R.id.poderes)
        motivacaoTextView = view.findViewById(R.id.motivacao)
        curiosidadeTextView = view.findViewById(R.id.curiosidade)


        val personagem = arguments?.getParcelable<Personagem>(ARG_PERSONAGEM)

        if (personagem != null) {
            nomePersonagem.text = personagem.nome
            nomeRealTextView.text = personagem.nomeReal
            heroiVilaoTextView.text = personagem.hv
            poderesTextView.text = personagem.poderes
            motivacaoTextView.text = personagem.motivacao
            curiosidadeTextView.text = personagem.curiosidade

        }

        return view
    }
}