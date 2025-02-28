package com.absolute.cinema.data.remote

import com.absolute.cinema.data.remote.respond.MovieDto
import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("results") val movies: List<MovieDto>
)