package com.absolute.cinema.data.local.movie

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface MovieDao {
    @Upsert
    suspend fun upsertMovieList(movieList: List<MovieEntity>)
}