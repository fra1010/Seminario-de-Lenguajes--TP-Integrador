package com.example.trabajopracticointegrador

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "usuarios_table")
data class Usuario (
    @ColumnInfo(name= "usuarioNombre") val usuarioNombre: String?,
    @ColumnInfo(name= "usuarioContrasenia") val usuarioContrasenia:String?,

    )
    {
        @PrimaryKey(autoGenerate = true) var id: Int = 0
    }
