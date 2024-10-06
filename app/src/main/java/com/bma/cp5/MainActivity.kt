package com.bma.cp5

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class MainActivity : AppCompatActivity() {
    private lateinit var btnCadastro: ImageView
    private lateinit var btnLogin: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCadastro = findViewById(R.id.btnCadastro)
        btnLogin = findViewById(R.id.btnLista)

        btnCadastro.setOnClickListener {
            val cadastroFragment = CadastroFragment()

            val bundle = bundleOf(
                "nomeAluno" to "Fabiola",
                "numFaltas" to 5
            )
            cadastroFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, cadastroFragment)
                .commit()
        }

        btnLogin.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ListaFragment())
                .commit()
        }


    }
}