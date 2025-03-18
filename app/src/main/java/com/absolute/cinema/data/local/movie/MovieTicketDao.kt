package com.absolute.cinema.data.local.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieTicketDao {
    @Insert
    suspend fun insert(ticket: MovieTicket)

    @Query("SELECT * FROM ticket_table")
    suspend fun getAllTickets(): List<MovieTicket>

    @Query("DELETE FROM ticket_table")
    suspend fun deleteAllTickets()
}