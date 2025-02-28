package com.absolute.cinema.data.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY = "f32fce1a0f7e63e9dbf9955dc127f899"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    val api: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}
