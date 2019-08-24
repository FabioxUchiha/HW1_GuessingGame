package com.example.hw1_guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_number_guess.*
import java.util.*
import kotlin.random.Random as Random1

class NumberGuessActivity : AppCompatActivity() {

    private var ayuda = 0
    private var jugadas = 0
    private var intentos = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)
        iniciarJuego()
    }


    private fun iniciarJuego() {

            val r = Random()
            ayuda = r.nextInt(1000) + 1
            //Toast.makeText(this, "el random es: "+ayuda, Toast.LENGTH_SHORT).show()
            jugadas = 0

            ingresaIntentos.text.clear()
            btnIniciar.isEnabled = true
            ingresaNumero.text.clear()
            txtMensaje.text = ""

    }


    fun jugarClick(view: View) {
        if (intentos==999){
            try {
                intentos = ingresaIntentos.text.toString().toInt()
            }catch (e: Exception){
                valorIntentos()
                return
            }
        }


        if (intentos<=0){
            Toast.makeText(this, "se terminaron los intentos, presione iniciar para jugar denuevo: ", Toast.LENGTH_SHORT).show()
        }else{
            val jugar: Int
            try {
                jugar = ingresaNumero.text.toString().toInt()
            } catch (e: Exception) {
                valorJuego()
                return
            }

            if (jugar !in 1..1000) {
                valorJuego()
                return
            }

            jugadas++
            intentos=intentos-1
            Toast.makeText(this, "su numero de intentos es: "+intentos, Toast.LENGTH_SHORT).show()



            when {
                jugar < ayuda -> txtMensaje.text = getString(R.string.alto)
                jugar > ayuda -> txtMensaje.text = getString(R.string.bajo)
                else -> {
                    txtMensaje.text = "el numero de intentos usados fue: $jugadas"
                    ingresaNumero.isEnabled = false
                }
        }
        }
    }

    private fun valorJuego() {
        Toast.makeText(this, "DEBE INGRESAR UN NUMERO ENTRE UNO Y MIL!", Toast.LENGTH_SHORT).show()
        ingresaNumero.text.clear()
        txtMensaje.text = ""
    }

    private fun valorIntentos() {
        Toast.makeText(this, "DEBE INGRESAR UN NUMERO DE INTENTOS!", Toast.LENGTH_SHORT).show()
        ingresaNumero.text.clear()
        txtMensaje.text = ""
    }

    fun iniciarClick(view: View) {
        intentos=999
        iniciarJuego()
    }

}
