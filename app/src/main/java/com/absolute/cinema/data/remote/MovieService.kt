package com.absolute.cinema.data.remote

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
    @GET("3/movie/6")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
        //@Path("movie_id") movieId: Int?,
    ): Response<MovieDetails>
}
