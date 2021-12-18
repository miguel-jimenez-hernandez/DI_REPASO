package com.example.ej_repaso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.ej_repaso.databinding.ActivityAdaptadorBinding

class Adaptador(
    val con: MainActivity,
    val peliculas:MutableList<Pelicula>

):
    RecyclerView.Adapter<Adaptador.ViewHolder>(),
    Filterable{

    class ViewHolder(val bind: ActivityAdaptadorBinding)
        : RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ActivityAdaptadorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = this.peliculas[position]

        holder.bind.adapTvTitulo.text = pelicula.titulo
        holder.bind.adapIvCaratula.setImageResource(pelicula.caratula)
        holder.bind.adapBtnVer.setOnClickListener{
            val activity = Intent(this.con,Detalles::class.java)
            activity.putExtra("PELICULA",pelicula)
            this.con.startActivity(activity)
        }

    }

    override fun getItemCount(): Int {
        return this.peliculas.size
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}