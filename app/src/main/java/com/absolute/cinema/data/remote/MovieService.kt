package com.absolute.cinema.data.remote

import com.absolute.cinema.data.remote.response.MovieDetailsDto
import com.absolute.cinema.data.remote.response.MovieVideoListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @Headers("Accept: application/json")
    @GET("3/movie/popular")
    suspend fun getMovies(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<Movies>

    @Headers("Accept: application/json")
    @GET("3/movie/{MOVIE_ID}")
    suspend fun getMovieDetails(
        @Header("Authorization") token: String,
        @Path("MOVIE_ID") movieId: String
    ): Response<MovieDetailsDto>

    @Headers("Accept: application/json")
    @GET("3/search/movie")
    suspend fun searchMovies(
        @Header("Authorization") token: String,
        @Query("query") movieTitle: String
    ): Response<Movies>

    @Headers("Accept: application/json")
    @GET("3/movie/{MOVIE_ID}/videos")
    suspend fun videoMovies(
        @Header("Authorization") token: String,
        @Path("MOVIE_ID") movieId: String
    ): Response<MovieVideoListDto>
}
