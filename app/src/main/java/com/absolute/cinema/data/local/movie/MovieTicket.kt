package com.absolute.cinema.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket_table")
data class MovieTicket(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: String,
    val ticketDate: String,
    val movieTitle: String,
    val cinemaName: String
)