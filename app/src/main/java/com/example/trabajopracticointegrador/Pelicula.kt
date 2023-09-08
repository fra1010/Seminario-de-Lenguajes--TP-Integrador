package com.example.trabajopracticointegrador

import android.os.Parcel
import android.os.Parcelable

data class Pelicula(
    val id: Int,
    val nombre: String?,
    val anio:String?,
    val director:String?,
    val genero:String?,
    val foto:Int,
    val sinopsis:String?



) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(anio)
        parcel.writeString(director)
        parcel.writeString(genero)
        parcel.writeInt(foto)
        parcel.writeString(sinopsis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pelicula> {
        override fun createFromParcel(parcel: Parcel): Pelicula {
            return Pelicula(parcel)
        }

        override fun newArray(size: Int): Array<Pelicula?> {
            return arrayOfNulls(size)
        }
    }
}
