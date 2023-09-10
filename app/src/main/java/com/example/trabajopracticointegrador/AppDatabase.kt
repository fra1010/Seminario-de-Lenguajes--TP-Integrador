package com.example.trabajopracticointegrador

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pelicula::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao
    companion object{

        private var INSTANCIA: AppDatabase?= null
        fun getDatabase(contexto: Context): AppDatabase{
            if(INSTANCIA== null){
                synchronized(this){
                    INSTANCIA= Room.databaseBuilder(
                    contexto, AppDatabase::class.java, "base_app_peliculas")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!

        }



    }

}