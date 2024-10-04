package com.bma.cp5

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnCadastro: Button
    private lateinit var btnLista: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCadastro = findViewById(R.id.btnCadastro)
        btnLista = findViewById(R.id.btnLogin)

        btnCadastro.setOnClickListener{
            val cadastroFragment = CadastroFragment()

            val bundle = bundleOf(
                "nome" to "Ana",
                "idade" to "19",
                "email" to "ana@gmail.com",
                "senha" to "moranguinho",
                "confirmarSenha" to "ana@gmail.com"
            )
            cadastroFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,cadastroFragment)
                .commit()
        }

        btnLista.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,ListaFragment())
                .commit()
        }




    }
}