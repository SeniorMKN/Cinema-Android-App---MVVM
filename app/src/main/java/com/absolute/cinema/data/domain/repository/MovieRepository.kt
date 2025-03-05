package com.absolute.cinema.data.domain.repository

import android.util.Log
import com.absolute.cinema.data.remote.MovieService
import com.absolute.cinema.data.remote.Movies
import com.absolute.cinema.data.remote.RetrofitInstance
import com.absolute.cinema.data.remote.response.MovieDto
import com.absolute.cinema.ui.utils.AUTH_TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {
    private val movieService: MovieService = RetrofitInstance.api

    suspend fun fetchMovies(): List<MovieDto>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<Movies> = movieService.getMovies(
                    token = AUTH_TOKEN
                )

                if (response.isSuccessful) {
                    Log.i("MovieRepository", "RESPONSE OK")
                    return@withContext response.body()?.movies
                } else {
                    Log.i("MovieRepository", "Error: ${response.code()}")
                    return@withContext null
                }
            } catch (e: Exception) {
                Log.i("MovieRepository", "Exception: ${e.message}")
                return@withContext null
            }
        }
    }
}

