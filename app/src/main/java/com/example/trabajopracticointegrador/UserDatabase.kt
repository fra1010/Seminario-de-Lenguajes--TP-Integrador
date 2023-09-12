package com.example.trabajopracticointegrador

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 1)
abstract class UserDatabase:RoomDatabase() {

    abstract fun userDao(): UsuarioDao
    companion object{

        private var INSTANCIA: UserDatabase?= null
        fun getDatabase(contexto: Context): UserDatabase{
            if(INSTANCIA== null){
                synchronized(this){
                    INSTANCIA= Room.databaseBuilder(
                        contexto, UserDatabase::class.java, "base_app_usuario")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }
    }

}