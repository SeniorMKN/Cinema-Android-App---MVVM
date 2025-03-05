package com.absolute.cinema.data.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.absolute.cinema.data.remote.MovieDetails
import com.absolute.cinema.data.remote.MovieService
import com.absolute.cinema.data.remote.Movies
import com.absolute.cinema.data.remote.RetrofitInstance
import com.absolute.cinema.data.remote.response.DetailsDto
import com.absolute.cinema.data.remote.response.MovieDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository {

    private val movieService: MovieService = RetrofitInstance.api

    private val _moviesLiveData = MutableLiveData<List<MovieDto>>()
    val moviesLiveData: LiveData<List<MovieDto>> get() = _moviesLiveData

    private val _moviesDetailsLiveData = MutableLiveData<DetailsDto>()
    val moviesDetailsLiveData: LiveData<DetailsDto> get() = _moviesDetailsLiveData

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

    suspend fun fetchMoviesDetails(movieId: Int?) {
        withContext(Dispatchers.IO) {
            try {
                val response: Response<MovieDetails> = movieService.getMovieDetails(
                    token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMzJmY2UxYTBmN2U2M2U5ZGJmOTk1NWRjMTI3Zjg5OSIsIm5iZiI6MTc0MDc0MTkxNy4yMTYsInN1YiI6IjY3YzE5ZDFkYzY3YjM2OGJhNjM1OGJkZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5LgeFIL123PAbki7cBEsybyGR55kkpzYJtqJxEbkYc8",
                    //movieId = movieId
                )

                if (response.isSuccessful) {
                    Log.i("MovieRepository", "DETAILS OK")
                    response.body()?.let { res ->
                        _moviesDetailsLiveData.postValue(res.moviesDetails)
                        Log.i("MovieRepository", res.moviesDetails.toString())
                    }
                } else {
                    Log.i("MovieRepository", "Error DETAILS: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.i("MovieRepository", "Exception: ${e.message}")
            }
        }
    }
}
