package com.example.trabajopracticointegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

@Suppress("DEPRECATION")
class DescripcionActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion)

        val peli = intent.getParcelableExtra<Pelicula>("pelicula")
        if (peli !=null) {
            val nombr: TextView= findViewById(R.id.tvDescripcionNombre)
            val imagen: ImageView = findViewById(R.id.ivDescripcionPelicula)
            val gener: TextView = findViewById(R.id.tvDescripcionGenero)
            val ani: TextView= findViewById(R.id.tvDescripcionAño)
            val directo: TextView= findViewById(R.id.tvDescripcionDirector)
            val sinopsi: TextView= findViewById(R.id.tvDescripcionSinopsis)

            nombr.text= peli.nombre
            imagen.setImageResource(peli.foto)
            gener.text=peli.genero
            ani.text=peli.anio
            directo.text=peli.director
            sinopsi.text=peli.sinopsis


        }

    }
}