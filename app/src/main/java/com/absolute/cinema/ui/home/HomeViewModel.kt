package com.absolute.cinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absolute.cinema.data.domain.repository.MovieRepository
import com.absolute.cinema.data.remote.response.MovieDetailsDto
import com.absolute.cinema.data.remote.response.MovieDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _moviesLiveData = MutableLiveData<List<MovieDto>>()
    val moviesLiveData: LiveData<List<MovieDto>> get() = _moviesLiveData

    fun fetchMovies() {
        viewModelScope.launch {
            val movies = movieRepository.fetchMovies()
            movies?.let {
                _moviesLiveData.postValue(it)
            }
        }
    }
}
