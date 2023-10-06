package com.example.trabajopracticointegrador
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuarioDao {
    @Query("select * from usuarios_table")
    fun getAllU():List<Usuario>
    @Insert
    fun insertUsuario(usuario: Usuario)

    @Query("select * from usuarios_table where usuarioNombre=:nombreLogin")
    fun getNombreUsuario(nombreLogin: String):Usuario

    @Query ("select * from usuarios_table where id like :id")
    fun getUsuarioData(id:Int):Usuario

}