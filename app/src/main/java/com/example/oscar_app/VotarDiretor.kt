package com.example.oscar_app

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.oscar_app.models.Diretor
import com.example.oscar_app.votoData.VotoData

class VotarDiretor : AppCompatActivity() {
    var diretores: Array<Diretor> = arrayOf(Diretor(1, "James Cameron"), Diretor(2, "Alfred Hitchcoc"), Diretor(4, "Steven Spielberg"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_votar_diretor)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btn = findViewById<Button>(R.id.button3)
        println("Diretor:" + VotoData.diretorName)

        for(diretor in diretores){
            val radioButton = RadioButton(this).apply {
                text = diretor.name
                id = diretor.id
            }
            radioGroup.addView(radioButton)
        }

        VotoData.diretorId?.let { savedId ->
            radioGroup.check(savedId)
        }

        btn.setOnClickListener{
            val selectedPessoa = diretores.find { it.id == radioGroup.checkedRadioButtonId }
            selectedPessoa?.let {
                VotoData.diretorId = selectedPessoa.id
                VotoData.diretorName = selectedPessoa.name
            }
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
}