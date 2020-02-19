package com.dalvarez.realidadaumentadakotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

class PoppupChair: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_poppup_chair)

        var medidasVentana: DisplayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(medidasVentana)

        var ancho: Int = medidasVentana.widthPixels
        var alto: Int = medidasVentana.heightPixels

        window.setLayout( ( (ancho * 0.8) as Int), ( (alto * 0.5) as Int) )
    }
}
