package com.example.trabajopracticointegrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.content.Context
import androidx.recyclerview.widget.RecyclerView


class PeliculaAdapter (private val peliculaLista:MutableList<Pelicula>, private  val context: Context )
    : RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>(){

    var onItemClick: ((Pelicula) -> Unit)? = null

    class PeliculaViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val foto: ImageView = itemView.findViewById(R.id.ivPelicula)
        val nombre: TextView= itemView.findViewById(R.id.tvPeliculaNombre)
        val director: TextView= itemView.findViewById(R.id.tvPeliculaDirector)
        val genero: TextView= itemView.findViewById(R.id.tvPeliculaGenero)
        val anio: TextView= itemView.findViewById(R.id.tvPeliculaAño)
        val sinopsis: TextView= itemView.findViewById(R.id.tvPeliculaSinopsis)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return PeliculaViewHolder(view)

    }

    override fun getItemCount(): Int {
        return peliculaLista.size
    }

    override fun onBindViewHolder(holder: PeliculaViewHolder, position: Int) {
        val pelicula = peliculaLista[position]
        holder.foto.setImageResource(pelicula.foto)
        holder.nombre.text= pelicula.nombre
        holder.director.text= pelicula.director
        holder.genero.text= pelicula.genero
        holder.anio.text= pelicula.anio
        //holder.sinopsis.text=pelicula.sinopsis

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(pelicula)
        }

    }

}