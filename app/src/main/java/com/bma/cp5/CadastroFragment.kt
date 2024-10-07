package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bma.cp5.bancodedados.PersonagemDAO
import com.bma.cp5.model.Personagem

class CadastroFragment : Fragment() {

    private lateinit var personagemEditText: EditText
    private lateinit var nomeRealEditText: EditText
    private lateinit var hvEditText: EditText
    private lateinit var poderesEditText: EditText
    private lateinit var motivacaoEditText: EditText
    private lateinit var curiosidadeEditText: EditText
    private lateinit var botaoCadastro: Button
    private lateinit var personagemDAO: PersonagemDAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)


        personagemEditText = view.findViewById(R.id.personagem)
        nomeRealEditText = view.findViewById(R.id.nomeReal)
        hvEditText = view.findViewById(R.id.hv)
        poderesEditText = view.findViewById(R.id.poderes)
        motivacaoEditText = view.findViewById(R.id.motivacao)
        curiosidadeEditText = view.findViewById(R.id.curiosidade)
        botaoCadastro = view.findViewById(R.id.botaoCadastro)


        personagemDAO = PersonagemDAO(requireContext())


        botaoCadastro.setOnClickListener {
            cadastrarPersonagem()
        }

        return view
    }

    private fun cadastrarPersonagem() {

        val nome = personagemEditText.text.toString().trim()
        val nomeReal = nomeRealEditText.text.toString().trim()
        val hv = hvEditText.text.toString().trim()
        val poderes = poderesEditText.text.toString().trim()
        val motivacao = motivacaoEditText.text.toString().trim()
        val curiosidade = curiosidadeEditText.text.toString().trim()


        if (nome.isEmpty() || nomeReal.isEmpty() || hv.isEmpty() || poderes.isEmpty() || motivacao.isEmpty() || curiosidade.isEmpty()) {
            Toast.makeText(requireContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            return
        }


        val novoPersonagem = Personagem(0, nome, nomeReal, hv, poderes, motivacao, curiosidade)


        if (personagemDAO.salvar(novoPersonagem)) {
            Toast.makeText(requireContext(), "Personagem cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

            personagemEditText.setText("")
            nomeRealEditText.setText("")
            hvEditText.setText("")
            poderesEditText.setText("")
            motivacaoEditText.setText("")
            curiosidadeEditText.setText("")
        } else {
            Toast.makeText(requireContext(), "Erro ao cadastrar personagem!", Toast.LENGTH_SHORT).show()
        }
    }
}