package com.example.trabajopracticointegrador.api

import com.example.trabajopracticointegrador.response.MoviesListResponse
import com.example.trabajopracticointegrador.response.DetailMovieResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Call<DetailMovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Call<MoviesListResponse>

}