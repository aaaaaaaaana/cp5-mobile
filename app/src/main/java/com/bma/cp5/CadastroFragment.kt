package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bma.cp5.bancodedados.PersonagemDAO
import com.bma.cp5.databinding.FragmentCadastroBinding
import com.bma.cp5.model.Personagem

class CadastroFragment : Fragment() {
    private lateinit var binding: FragmentCadastroBinding
    private lateinit var personagemDAO: PersonagemDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personagemDAO = PersonagemDAO(requireContext())

        binding.botaoCadastro.setOnClickListener {
            val nome = binding.personagem.text.toString()
            val nomeReal = binding.nomeReal.text.toString()
            val hv = binding.hv.text.toString()
            val poderes = binding.poderes.text.toString()
            val motivacao = binding.motivacao.text.toString()
            val curiosidade = binding.curiosidade.text.toString()


            val novoPersonagem = Personagem(nome = nome, nomeReal = nomeReal, hv = hv, poderes = poderes, motivacao = motivacao, curiosidade = curiosidade)


            if (personagemDAO.salvar(novoPersonagem)) {
                Toast.makeText(requireContext(), "Personagem cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                binding.personagem.setText("")
                binding.nomeReal.setText("")
                binding.hv.setText("")
                binding.poderes.setText("")
                binding.motivacao.setText("")
                binding.curiosidade.setText("")

            } else {
                Toast.makeText(requireContext(), "Erro ao cadastrar personagem.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}