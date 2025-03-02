package com.absolute.cinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absolute.cinema.data.domain.repository.MovieRepository
import com.absolute.cinema.data.remote.response.MovieDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val movieRepository = MovieRepository()

    val moviesLiveData: LiveData<List<MovieDto>> get() = movieRepository.moviesLiveData

    fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchMovies()
        }
    }
}
