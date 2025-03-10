package com.absolute.cinema.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieVideoListDto(
    @SerializedName("results") val results: List<Result>
)