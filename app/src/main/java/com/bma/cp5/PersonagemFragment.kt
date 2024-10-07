package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bma.cp5.bancodedados.PersonagemDAO
import com.bma.cp5.model.Personagem

class PersonagemFragment : Fragment() {

    private lateinit var nomePersonagem: TextView
    private lateinit var nomeRealTextView: TextView
    private lateinit var heroiVilaoTextView: TextView
    private lateinit var poderesTextView: TextView
    private lateinit var motivacaoTextView: TextView
    private lateinit var curiosidadeTextView: TextView
    private lateinit var btnAtualizar: ImageView
    private lateinit var btnDeletar: ImageView
    private lateinit var personagemDAO: PersonagemDAO
    private lateinit var sharedViewModel: ListaFragmentViewModel

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
        btnAtualizar = view.findViewById(R.id.btnAtualizar)
        btnDeletar = view.findViewById(R.id.btnDeletar)

        personagemDAO = PersonagemDAO(requireContext())

        // Inicializar o sharedViewModel aqui
        sharedViewModel = ViewModelProvider(requireActivity()).get(ListaFragmentViewModel::class.java)

        val personagem = arguments?.getParcelable<Personagem>(ARG_PERSONAGEM)

        if (personagem != null) {
            nomePersonagem.text = personagem.nome
            nomeRealTextView.text = personagem.nomeReal
            heroiVilaoTextView.text = personagem.hv
            poderesTextView.text = personagem.poderes
            motivacaoTextView.text = personagem.motivacao
            curiosidadeTextView.text = personagem.curiosidade
        }


        btnAtualizar.setOnClickListener {
            val nome = nomePersonagem.text.toString()
            val nomeReal = nomeRealTextView.text.toString()
            val hv = heroiVilaoTextView.text.toString()
            val poderes = poderesTextView.text.toString()
            val motivacao = motivacaoTextView.text.toString()
            val curiosidade = curiosidadeTextView.text.toString()
            val personagemAtualizado = Personagem(
                id = id,
                nome = nome,
                nomeReal = nomeReal,
                hv = hv,
                poderes = poderes,
                motivacao = motivacao,
                curiosidade = curiosidade
            )
            if (personagemDAO.atualizar(personagemAtualizado)) {
                Toast.makeText(requireContext(), "Personagem atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                sharedViewModel.atualizarLista()
            } else {
                Toast.makeText(requireContext(), "Erro ao atualizar personagem.", Toast.LENGTH_SHORT).show()
            }
        }

        btnDeletar.setOnClickListener {
            if (personagem != null) {
                if (personagemDAO.remover(personagem.id)) {
                    Toast.makeText(requireContext(), "Personagem deletado com sucesso!", Toast.LENGTH_SHORT).show()
                    sharedViewModel.atualizarLista()
                } else {
                    Toast.makeText(requireContext(), "Erro ao deletar personagem.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }
}

class ListaFragmentViewModel : ViewModel() {
    private val _listaAtualizada = MutableLiveData<Boolean>()
    val listaAtualizada: LiveData<Boolean> = _listaAtualizada

    fun atualizarLista() {
        _listaAtualizada.value = true
    }
}