package com.absolute.cinema.data.remote

import androidx.lifecycle.ViewModel

class MoviesSharedViewModel : ViewModel() {
    private var selectedMovieId: String = ""
    private var selectedMovieTitle: String = ""
    private var selectedMovieDate: String = ""
    private var selectedMovieTime: String = ""

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

    fun setSelectedDate(selectedDate: String) {
        selectedMovieDate = selectedDate
    }

    fun getSelectedDate(): String {
        return selectedMovieDate
    }

    fun setSelectedTime(selectedTime: String) {
        selectedMovieTime = selectedTime
    }

    fun getSelectedTime(): String {
        return selectedMovieTime
    }
}
