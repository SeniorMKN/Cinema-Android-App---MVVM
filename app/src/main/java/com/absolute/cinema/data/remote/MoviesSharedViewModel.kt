package com.absolute.cinema.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoviesSharedViewModel : ViewModel() {
    private val _selectedMovieId = MutableLiveData<Int>()
    val selectedMovieId: LiveData<Int> get() = _selectedMovieId

    private val _selectedMovieTitle = MutableLiveData<String>()
    val selectedMovieTitle: LiveData<String> get() = _selectedMovieTitle

    fun setSelectedMovieId(movieId: Int) {
        _selectedMovieId.value = movieId
    }

    fun setSelectedMovieTitle(movieTitle: String) {
        _selectedMovieTitle.value = movieTitle
    }
}
