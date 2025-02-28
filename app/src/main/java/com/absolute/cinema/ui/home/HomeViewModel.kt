package com.absolute.cinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import android.util.Log
import com.absolute.cinema.data.remote.MovieService
import com.absolute.cinema.data.remote.Movies
import com.absolute.cinema.data.remote.RetrofitInstance
import com.absolute.cinema.data.remote.respond.MovieDto
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val movieService: MovieService = RetrofitInstance.api

    val moviesLiveData: LiveData<List<MovieDto>> = liveData {
        try {
            val response: Response<Movies> = movieService.getMovies(
                token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMzJmY2UxYTBmN2U2M2U5ZGJmOTk1NWRjMTI3Zjg5OSIsIm5iZiI6MTc0MDc0MTkxNy4yMTYsInN1YiI6IjY3YzE5ZDFkYzY3YjM2OGJhNjM1OGJkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5LgeFIL123PAbki7cBEsybyGR55kkpzYJtqJxEbkYc8",
            )
            if (response.isSuccessful) {
                Log.i("TAG_MOVIE", "RESPONSE OK")
                response.body()?.let { res ->
                    emit(res.movies)
                    Log.i("TAG_MOVIE", res.movies.toString())
                }
            } else {
                Log.i("HomeViewModel", "Error: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.i("HomeViewModel", "Exception: ${e.message}")
        }
    }
}