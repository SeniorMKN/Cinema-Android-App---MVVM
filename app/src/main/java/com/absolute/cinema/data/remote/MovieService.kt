package com.absolute.cinema.data.remote

import com.absolute.cinema.data.remote.response.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface MovieService {

    @Headers("Accept: application/json")
    @GET("3/movie/popular")
    suspend fun getMovies(
        @Header("Authorization") token: String,
    ): Response<Movies>

    @Headers("Accept: application/json")
    @GET("3/movie/{MOVIE_ID}")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("MOVIE_ID") movieId: String
    ): Response<MovieDetailsDto>
}
