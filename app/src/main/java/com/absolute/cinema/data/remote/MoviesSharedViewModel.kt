package com.absolute.cinema.data.remote

import androidx.lifecycle.ViewModel

class MoviesSharedViewModel : ViewModel() {
    private var selectedMovieId: String = ""
    private var selectedMovieTitle: String = ""


    fun setSelectedMovieId(movieId: String) {
        selectedMovieId = movieId
    }

    fun getSelectedMovieId(): String {
        return selectedMovieId
    }

    fun setSelectedMovieTitle(movieTitle: String) {
        selectedMovieTitle = movieTitle
    }

    fun getSelectedMovieTitle(): String {
        return selectedMovieTitle
    }
}
