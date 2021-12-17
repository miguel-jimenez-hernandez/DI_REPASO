package com.example.ej_repaso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ej_repaso.databinding.ActivityDetallesBinding

class Detalles : AppCompatActivity() {
    lateinit var bind: ActivityDetallesBinding
    lateinit var pelicula: Pelicula
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bind = ActivityDetallesBinding.inflate(layoutInflater)
        val view = this.bind.root
        setContentView(view)
        this.pelicula = intent.getParcelableExtra("PELICULA")?: Pelicula("",0,0.0,0)
    }

    override fun onStart() {
        super.onStart()

        this.bind.detallesIvCaratula.setImageResource(this.pelicula.caratula)
        this.bind.detallesTvTitulo.text = this.pelicula.titulo
        var pegi = this.pelicula.getImg()
        this.bind.detallesIvPegi.setImageResource(pegi)

    }
}