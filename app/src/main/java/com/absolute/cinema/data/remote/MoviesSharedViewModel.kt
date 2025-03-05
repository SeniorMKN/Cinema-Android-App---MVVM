package com.absolute.cinema.data.remote

import androidx.lifecycle.ViewModel

class MoviesSharedViewModel : ViewModel() {

    private var selectedMovieId: Int? = null
    private var selectedMovieTitle: String? = null

    fun setSelectedMovieId(movieId: Int) {
        selectedMovieId = movieId
    }

    fun getSelectedMovieId(): Int? {
        return selectedMovieId
    }

    fun setSelectedMovieTitle(movieTitle: String) {
        selectedMovieTitle = movieTitle
    }

    fun getSelectedMovieTitle(): String? {
        return selectedMovieTitle
    }
}
