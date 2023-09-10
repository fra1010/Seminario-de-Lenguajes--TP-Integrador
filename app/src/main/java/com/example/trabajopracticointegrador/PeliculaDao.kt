package com.example.trabajopracticointegrador

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeliculaDao {
    @Query("select * from peliculas_table")
    fun getAll():List<Pelicula>
    @Insert
    fun insertPelicula(pelicula: Pelicula)
}