package com.bma.cp5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bma.cp5.bancodedados.PersonagemDAO
import com.bma.cp5.databinding.FragmentListaBinding
import com.bma.cp5.model.Personagem

class ListaFragment : Fragment() {
    private lateinit var binding: FragmentListaBinding
    private lateinit var personagemDAO: PersonagemDAO
    private lateinit var adapter: PersonagemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        personagemDAO = PersonagemDAO(requireContext())


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PersonagemAdapter(emptyList()) { personagem ->

            childFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerPersonagem, PersonagemFragment.newInstance(personagem))
                .commit()
        }
        binding.recyclerView.adapter = adapter


        loadPersonagens()
    }

    private fun loadPersonagens() {
        val personagens = personagemDAO.listar()
        adapter.updatePersonagens(personagens)
    }
}