package com.bma.cp5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class CadastroFragment : Fragment() {

    private lateinit var btnEnviar:Button

    private var nomeAluno:String? = null
    private var numFaltas:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nomeAluno = arguments?.getString("nomeAluno")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cadastro, container, false)

        btnEnviar = view.findViewById(R.id.botaoCadastro)



        btnEnviar.setOnClickListener{
            nomeAluno = arguments?.getString("nomeAluno")
            numFaltas = arguments?.getInt("numFaltas")

            Log.i("teste","Nome:$nomeAluno - Número de faltas:$numFaltas")
        }




        return view
    }

}