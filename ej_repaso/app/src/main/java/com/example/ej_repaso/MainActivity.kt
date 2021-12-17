package com.example.ej_repaso

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej_repaso.databinding.ActivityMainBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pelicula(val titulo:String, val caratula: Int, val precio: Double, val pegi: Int):
    Parcelable{
        fun getImg(): Int{
            val res = when(this.pegi){
                3->R.drawable.pegi3
                7->R.drawable.pegi7
                12->R.drawable.pegi12
                16->R.drawable.pegi16
                18->R.drawable.pegi18
                else-> throw Exception()
            }
            return res
        }
    }

class MainActivity : AppCompatActivity() {
    lateinit private var bind: ActivityMainBinding
    lateinit var peliculas: MutableList<Pelicula>
    lateinit var pegis: TypedArray
    lateinit var caratulas: TypedArray
//    @SuppressLint("ResourceType")
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.bind = ActivityMainBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)
        this.pegis = resources.obtainTypedArray(R.array.pegis)
        this.caratulas = resources.obtainTypedArray(R.array.caratulas)
        val aux = mutableListOf(
            Pelicula("La Guerra de las Galaxias",this.caratulas.getResourceId(0,0),12.80, 12),
            Pelicula("Indiana Jones",this.caratulas.getResourceId(1,0),8.80, 12),
            Pelicula("Saló",this.caratulas.getResourceId(2,0),12.80, 18),
            Pelicula("La abeja Maya",this.caratulas.getResourceId(3,0),12.80, 3),
            Pelicula("La familia Adams",this.caratulas.getResourceId(4,0),12.80, 7),
            Pelicula("Somos los Miller",this.caratulas.getResourceId(5,0),12.80, 16)
        )
        this.peliculas = intent.getParcelableArrayListExtra("PELICULAS")?: aux

    }

    override fun onStart() {
        super.onStart()

        val adaptador = Adaptador(this,peliculas)
        this.bind.mainRv.adapter = adaptador
        this.bind.mainRv.layoutManager= LinearLayoutManager(this)
        this.bind.mainBtn.setOnClickListener {
            val activity = Intent(this,Añadir::class.java)
            startActivity(activity)
        }

    }
    fun anadirItem(){

//        this.bind.rv.adapter?.notifyDataSetChanged()
    }
}