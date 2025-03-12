package com.absolute.cinema.data.model

import java.util.Date

data class TicketItemModel(
    val timeMovieStart : String,
    val qualityCinema : String,
    val cinemaName : String,
    val adultPrice : String,
    val childPrice : String,
    val studentPrice : String,
    val vipPrice : String,
    val date : String,
    val address : String
)
