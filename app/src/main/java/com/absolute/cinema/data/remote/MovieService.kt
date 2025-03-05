package com.absolute.cinema.data.remote

import com.absolute.cinema.data.remote.response.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface MovieService {

    @Headers("Accept: application/json")
    @GET("3/movie/popular")
    suspend fun getMovies(
        @Header("Authorization") token: String,
    ): Response<Movies>

    @Headers("Accept: application/json")
    @GET("3/movie/6")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
    ): Response<MovieDetailsDto>
}
