package com.bma.cp5

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnCadastro: ImageView
    private lateinit var btnLista: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnCadastro = findViewById(R.id.btnCadastro)
        btnLista = findViewById(R.id.btnLista)


        showFragment(ListaFragment())

        btnCadastro.setOnClickListener {
            showFragment(CadastroFragment())
        }

        btnLista.setOnClickListener {
            showFragment(ListaFragment())
        }
    }


    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .commit()
    }

    fun atualizarLista() {

        val listaFragment = supportFragmentManager.findFragmentByTag("ListaFragment") as? ListaFragment
        listaFragment?.atualizarLista()
    }
}