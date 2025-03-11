package com.absolute.cinema.data.domain.repository

import android.util.Log
import com.absolute.cinema.data.remote.MovieService
import com.absolute.cinema.data.remote.Movies
import com.absolute.cinema.data.remote.RetrofitInstance
import com.absolute.cinema.data.remote.response.MovieDetailsDto
import com.absolute.cinema.data.remote.response.MovieDto
import com.absolute.cinema.data.remote.response.MovieVideoListDto
import com.absolute.cinema.data.remote.response.Result
import com.absolute.cinema.ui.utils.AUTH_TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {
    private val movieService: MovieService = RetrofitInstance.api

    suspend fun fetchMovies(page: Int): List<MovieDto>? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<Movies> = movieService.getMovies(
                    token = AUTH_TOKEN,
                    page = page
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

    suspend fun loadMovieDetails(movieId: String): MovieDetailsDto? {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<MovieDetailsDto> = movieService.getMovieDetails(
                    token = AUTH_TOKEN,
                    movieId = movieId
                )

                if (response.isSuccessful) {
                    Log.i("MovieRepository", "RESPONSE OK")
                    return@withContext response.body()
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

    suspend fun searchMovies(movieTitle: String): List<MovieDto>?  {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<Movies> = movieService.searchMovies(
                    token = AUTH_TOKEN,
                    movieTitle = movieTitle
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

    suspend fun videoMovies(movieId: String): List<Result>?  {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<MovieVideoListDto> = movieService.videoMovies(
                    token = AUTH_TOKEN,
                    movieId = movieId
                )

                if (response.isSuccessful) {
                    Log.i("MovieRepository", "RESPONSE OK")
                    return@withContext response.body()?.results
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

