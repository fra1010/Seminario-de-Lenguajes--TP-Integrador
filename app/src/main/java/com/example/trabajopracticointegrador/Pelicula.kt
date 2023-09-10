package com.example.trabajopracticointegrador

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "peliculas_table")
data class Pelicula(
    @ColumnInfo(name= "nombre") val nombre: String?,
    @ColumnInfo(name= "anio") val anio:String?,
    @ColumnInfo(name="director") val director:String?,
    @ColumnInfo(name="genero") val genero:String?,
    @ColumnInfo(name="foto") val foto:Int,
    @ColumnInfo(name="sinopsis") val sinopsis:String?

) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}