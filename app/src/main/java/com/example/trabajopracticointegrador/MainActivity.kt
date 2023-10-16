package com.example.trabajopracticointegrador


import androidx.recyclerview.widget.LinearLayoutManager
import  androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.trabajopracticointegrador.adapter.MoviesAdapter
import com.example.trabajopracticointegrador.api.ApiClient
import com.example.trabajopracticointegrador.api.ApiService
import com.example.trabajopracticointegrador.databinding.ActivityMainBinding
import com.example.trabajopracticointegrador.response.MoviesListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.Manifest
import android.annotation.SuppressLint



class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        saludarUsuario()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.toolbar_texto)




        lifecycleScope.launch {//corutina

            binding.apply {

                progressBarMain.visibility = View.VISIBLE
                withContext(Dispatchers.IO){

                    val callMovieApi = api.getPopularMovie(1)
                    callMovieApi.enqueue(object : Callback<MoviesListResponse> {
                        override fun onResponse(
                            call: Call<MoviesListResponse>,
                            response: Response<MoviesListResponse>
                        ) {
                            progressBarMain.visibility = View.GONE
                            when (response.code()) {
                                //Response exitosa
                                in 200..299 -> {

                                    Log.d("Response Code", " success messages : ${response.code()}")

                                    response.body()?.let { itBody ->
                                        itBody.results.let { itData ->
                                            if (itData.isNotEmpty()) {
                                                moviesAdapter.differ.submitList(itData)
                                                //RecyclerMain
                                                rvMain.apply {
                                                    layoutManager = LinearLayoutManager(this@MainActivity)
                                                    adapter = moviesAdapter

                                                }
                                            }
                                        }
                                    }
                                }
                                //Redireccion
                                in 300..399 -> {
                                    Log.d("Response Code", " Redirection messages : ${response.code()}")
                                }
                                //Error Cliente
                                in 400..499 -> {
                                    Log.d("Response Code", " Client error responses : ${response.code()}")
                                }
                                //Error Servidor
                                in 500..599 -> {
                                    Log.d("Response Code", " Server error responses : ${response.code()}")
                                }
                            }
                        }

                        override fun onFailure(call: Call<MoviesListResponse>, t: Throwable) {
                            progressBarMain.visibility = View.GONE
                            Log.e("onFailure", "Err : ${t.message}") //mensaje de error

                        }


                    })
                }

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_logout) {
            var preferencias =
                getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
            preferencias.edit().clear().apply()
            Toast.makeText(this, "Se dejó de recordar el usuario",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun saludarUsuario() {
        var bundle: Bundle? = intent.extras
        if (bundle != null) {
            var usuario = bundle?.getString(resources.getString(R.string.usuario))
            Toast.makeText(this, "Bienvenido/a $usuario", Toast.LENGTH_SHORT).show()
        }
    }
}