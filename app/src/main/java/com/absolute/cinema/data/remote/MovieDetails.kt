package com.absolute.cinema.data.remote

import com.absolute.cinema.data.remote.response.DetailsDto
import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("details") val moviesDetails: DetailsDto
)
