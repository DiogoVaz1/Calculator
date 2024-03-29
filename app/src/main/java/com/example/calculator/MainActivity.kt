package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var buttons: Map<String, Button>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.textView)
        display.text = ""

        buttons = mapOf(
            Pair("0",findViewById(R.id.button0)),
            Pair("1",findViewById(R.id.button1)),
            Pair("2",findViewById(R.id.button2)),
            Pair("3",findViewById(R.id.button3)),
            Pair("4",findViewById(R.id.button4)),
            Pair("5",findViewById(R.id.button5)),
            Pair("6",findViewById(R.id.button6)),
            Pair("7",findViewById(R.id.button7)),
            Pair("8",findViewById(R.id.button8)),
            Pair("9",findViewById(R.id.button9)),
            Pair("-",findViewById(R.id.buttonminus)),
            Pair("+",findViewById(R.id.buttonplus)),
            Pair("enter",findViewById(R.id.buttonenter)),
            Pair("delete",findViewById(R.id.buttondelete)),
            Pair("×",findViewById(R.id.buttonmultiply)),
            Pair("÷",findViewById(R.id.buttondivide))
        )

        var valor1Str = ""
        var valor1 : Int? = null
        var valor2Str = ""
        var valor2 : Int? = null
        var valorFinal = 0
        var conta = ""
        var contador = 0



        for (i in buttons){
            i.value.setOnClickListener {

                when(i.key){

                    "-" -> {

                        //Se o primeiro numero for necessario, este 1º if deixa colocar um -
                        if(valor1Str == "") {
                            display.append(i.key)
                            valor1Str = valor1Str.plus(i.key)

                        //else if quando se clica 2x no - o simbolo é removido da conta
                        }else if(valor1Str == "-") {
                            valor1Str = ""
                            display.text = ""

                        //Caso ja exista o 1º valor é levado para a conta
                        }else{
                            display.append(i.key)
                            conta = "sub"

                            //OAJFIAHOFuqiwphQWPIU DupviEWyivd GwyiV ygi
                            /*if (valor1 != null){
                                if(contador % 3 == 0){
                                    var text = display.text
                                    text.substring(0, text.length -1)
                                    contador++
                                }else{
                                    display.append(i.key)
                                    contador++
                                }
                            }*/


                            /*Após a primeira conta o valor da string será "nada", caso se queira fazer mais alguma conta com o valor da conta
                            Este if deixa que o valor final dessa conta seja utilizado como primeiro numero da conta*/
                            if (valor1Str == "nada"){
                                valor1 = valorFinal
                                //Caso a string nao tenha o "nada", será apenas colocado o valor normal no Int valor1
                            }else {
                                valor1 = valor1Str.toInt()
                            }
                        }
                    }

                    "+" -> {
                        if (valor1Str != ""){
                            display.append(i.key)
                            conta = "soma"
                            if (valor1Str == "nada"){
                                valor1 = valorFinal
                            }else{
                                valor1 = valor1Str.toInt()
                            }
                        }

                    }

                    "÷" ->{
                        if (valor1Str != ""){
                            display.append(i.key)
                            conta = "dividir"
                            if (valor1Str == "nada"){
                                valor1 = valorFinal
                            }else{
                                valor1 = valor1Str.toInt()
                            }
                        }


                    }

                    "×" ->{
                        if (valor1Str != ""){
                            display.append(i.key)
                            conta = "multiplicar"
                            if (valor1Str == "nada"){
                                valor1 = valorFinal
                            }else{
                                valor1 = valor1Str.toInt()
                            }
                        }

                    }

                    "enter" -> {
                        if (valor2Str != "") {
                            valor2 = valor2Str.toInt()
                            valor2Str = ""

                            valorFinal = fazerConta(conta, valor1, valor2!!, valorFinal)
                            display.text = valorFinal.toString()
                            valor1Str = "nada"
                            conta = ""
                            valor1 = valorFinal
                        }
                    }

                    "delete" -> {
                        display.text = ""
                        valor1 = null
                        valor2 = null
                        valor1Str = ""
                        valor2Str = ""
                        valorFinal = 0


                    }

                    else -> {
                        display.append(i.key)
                        if (valor1 == null){
                            valor1Str = valor1Str.plus(i.key)
                        }else{
                            valor2Str = valor2Str.plus(i.key)
                        }
                    }
                }
            }
        }

    }

    //Apenas usado quando é clicado o botao Enter
    private fun fazerConta(conta: String,valor1: Int?, valor2: Int, valorFinal: Int) : Int {
        var finalFinalValue = 0
        when(conta){
            "soma"->{
                finalFinalValue = valor1!! + valor2
            }

            "sub"->{
                finalFinalValue = valor1!! - valor2
            }

            "multiplicar"->{
                finalFinalValue = valor1!! * valor2
            }

            "dividir"->{
                finalFinalValue = valor1!! / valor2
            }
        }

        return finalFinalValue
    }
}