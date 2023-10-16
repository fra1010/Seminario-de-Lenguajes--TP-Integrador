package com.example.trabajopracticointegrador


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.size.Scale
import com.example.trabajopracticointegrador.R
import com.example.trabajopracticointegrador.api.ApiClient
import com.example.trabajopracticointegrador.api.ApiService
import com.example.trabajopracticointegrador.databinding.ActivityDescripcionBinding
import com.example.trabajopracticointegrador.response.DetailMovieResponse
import com.example.trabajopracticointegrador.utilities.Constants.POSTER_BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DescripcionActivity : AppCompatActivity() {

    lateinit var binding: ActivityDescripcionBinding

    private val api: ApiService by lazy {
        ApiClient().getClient().create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescripcionBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val movieId: Int = intent.getIntExtra("id", 1)

        binding.apply {
            //mostrar cargando
            progressBarDescripcion.visibility = View.VISIBLE
            //llamar a la api de peliculas
            val callMoviesApi = api.getMovieDetails(movieId)

            callMoviesApi.enqueue(object : Callback<DetailMovieResponse> {
                override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                    Log.e("onFailure", "Err : ${response.code()}")

                    progressBarDescripcion.visibility = View.GONE

                    when (response.code()) {
                        in 200..299 -> {

                            response.body()?.let { itBody ->
                                val moviePosterURL = POSTER_BASE_URL + itBody.posterPath
                                ivDescripcionPelicula.load(moviePosterURL) {
                                    crossfade(true)
                                    placeholder(R.drawable.poster_placeholder)

                                    scale(Scale.FILL)


                                }
                                //imgMovieBack.load(moviePosterURL) {
                                //  crossfade(true)
                                //placeholder(R.drawable.poster_placeholder)
                                //scale(Scale.FILL)
                                //scale(Scale.FILL)
                                // xml android:scaleType="fitXY"
                                // xml android:scaleType="centerCrop"


                                tvDescripcionNombre.text = itBody.title
                                tvDescripcionAO.text = itBody.releaseDate
                                tvRating.text = itBody.voteAverage.toString()
                                //tvPresupuesto.text = itBody.budget.toString()
                                tvRecaudacion.text = "%,d".format(itBody.revenue)
                                tvDescripcionSinopsis.text = itBody.overview
                                tvDescripcionDuracion.text= itBody.runtime.toString()
                            }
                        }

                        in 300..399 -> {
                            Log.d("Response Code", " Redirection messages : ${response.code()}")
                        }

                        in 400..499 -> {
                            Log.d("Response Code", " Client error responses : ${response.code()}")
                        }

                        in 500..599 -> {
                            Log.d("Response Code", " Server error responses : ${response.code()}")
                        }

                    }
                }
                override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                    progressBarDescripcion.visibility = View.GONE
                    Log.e("onFailure", "Err : ${t.message}")
                }

            })
        }


    }

}

