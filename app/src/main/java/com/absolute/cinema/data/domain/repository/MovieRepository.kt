package com.absolute.cinema.data.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.absolute.cinema.data.remote.MovieService
import com.absolute.cinema.data.remote.Movies
import com.absolute.cinema.data.remote.RetrofitInstance
import com.absolute.cinema.data.remote.respond.MovieDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {

    private val movieService: MovieService = RetrofitInstance.api

    private val _moviesLiveData = MutableLiveData<List<MovieDto>>()
    val moviesLiveData: LiveData<List<MovieDto>> get() = _moviesLiveData

    suspend fun fetchMovies() {
        withContext(Dispatchers.IO) {
            try {
                val response: Response<Movies> = movieService.getMovies(
                    token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMzJmY2UxYTBmN2U2M2U5ZGJmOTk1NWRjMTI3Zjg5OSIsIm5iZiI6MTc0MDc0MTkxNy4yMTYsInN1YiI6IjY3YzE5ZDFkYzY3YjM2OGJhNjM1OGJkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5LgeFIL123PAbki7cBEsybyGR55kkpzYJtqJxEbkYc8"
                )

                if (response.isSuccessful) {
                    Log.i("MovieRepository", "RESPONSE OK")
                    response.body()?.let { res ->
                        _moviesLiveData.postValue(res.movies)
                        Log.i("MovieRepository", res.movies.toString())
                    }
                } else {
                    Log.i("MovieRepository", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.i("MovieRepository", "Exception: ${e.message}")
            }
        }
    }
}
