package com.dalvarez.realidadaumentadakotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
class PoppupTable: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_poppup_mesa)

        var medidasVentana: DisplayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(medidasVentana)

        var ancho : Int = medidasVentana.widthPixels
        var alto: Int = medidasVentana.heightPixels

        window.setLayout( ( (ancho * 0.8) as Int), ( (alto * 0.5) as Int) )

    }
}