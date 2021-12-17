package com.example.ej_repaso

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ej_repaso.databinding.ActivityAnadirBinding

class Añadir : AppCompatActivity() {
    lateinit var bind: ActivityAnadirBinding

    lateinit var defaults: TypedArray
    lateinit var peliculas: MutableList<Pelicula>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bind = ActivityAnadirBinding.inflate(layoutInflater)
        val view = this.bind.root
        setContentView(view)

        this.peliculas = intent.getParcelableArrayListExtra("PELICULAS")?: mutableListOf()
        this.defaults = resources.obtainTypedArray(R.array.defaults)

    }

    override fun onStart() {
        super.onStart()

        this.bind.aAdirBtn.setOnClickListener{
            val nombre = this.bind.aAdirEtNombre.text.toString()
            val precio = this.bind.aAdirEtPrecio.text.toString().toDoubleOrNull()
            if(nombre.trim() == ""){
                this.bind.aAdirEtNombre.error = "El nombre no puede quedar vacio"
            } else if(precio == null || precio < 0.0){
                this.bind.aAdirEtPrecio.error = "El precio debe ser positivo"
            } else {
                val caratula = this.defaults.getResourceId(0,0)
                val nueva = Pelicula(nombre,caratula,precio,3)
                this.peliculas.add(nueva)

                val activity = Intent(this, MainActivity::class.java)
                activity.putExtra("PELICULAS",ArrayList(this.peliculas))
                startActivity(activity)
            }
        }
    }
}