package com.bma.cp5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bma.cp5.bancodedados.PersonagemDAO
import com.bma.cp5.databinding.FragmentCadastroBinding
import com.bma.cp5.model.Personagem
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CadastroFragment : Fragment() {
    private lateinit var binding: FragmentCadastroBinding
    private lateinit var personagemDAO: PersonagemDAO
    private lateinit var selectImageLauncher: ActivityResultLauncher<Intent>

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

        selectImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val imageUri = data.data

                    binding.fotoPersonagem.setImageURI(imageUri)
                }
            }
        }

        binding.selecionarFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            selectImageLauncher.launch(intent)
        }

        binding.botaoCadastro.setOnClickListener {
            val nome = binding.personagem.text.toString()
            val nomeReal = binding.nomeReal.text.toString()
            val hv = binding.hv.text.toString()
            val poderes = binding.poderes.text.toString()
            val motivacao = binding.motivacao.text.toString()
            val curiosidade = binding.curiosidade.text.toString()

            val imageUri = binding.fotoPersonagem.drawable.current?.let {
                it.toString()
            }

            val contentUri = binding.fotoPersonagem.drawable.current?.let {
                it.toString()
            }

            if (contentUri != null) {
                val novoPersonagem = Personagem(
                    nome = nome,
                    nomeReal = nomeReal,
                    hv = hv,
                    poderes = poderes,
                    motivacao = motivacao,
                    curiosidade = curiosidade,
                    foto = contentUri
                )

                if (personagemDAO.salvar(novoPersonagem)) {
                    Toast.makeText(
                        requireContext(),
                        "Personagem cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    limparCampos()

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Erro ao cadastrar personagem.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Selecione uma foto para o personagem",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun limparCampos() {
        binding.personagem.setText("")
        binding.nomeReal.setText("")
        binding.hv.setText("")
        binding.poderes.setText("")
        binding.motivacao.setText("")
        binding.curiosidade.setText("")
        binding.fotoPersonagem.setImageResource(android.R.color.transparent)
    }


    private fun createTempFile(prefix: String, suffix: String): File {
        return File.createTempFile(prefix, suffix, requireContext().cacheDir)
    }

    private fun copyImageToFile(imageUri: Uri, tempFile: File) {
        try {
            requireContext().contentResolver.openInputStream(imageUri)?.use { inputStream ->
                FileOutputStream(tempFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
        } catch (e: IOException) {
            Toast.makeText(requireContext(), "Erro ao copiar a imagem", Toast.LENGTH_SHORT).show()
        }
    }
}