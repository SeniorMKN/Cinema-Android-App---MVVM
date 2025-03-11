package com.absolute.cinema.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.absolute.cinema.data.domain.repository.MovieRepository
import com.absolute.cinema.data.remote.response.MovieDto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val movieRepository = MovieRepository()

    private val _moviesLiveData = MutableLiveData<List<MovieDto>>()
    val moviesLiveData: LiveData<List<MovieDto>> get() = _moviesLiveData

    private val _searchMoviesLiveData = MutableLiveData<List<MovieDto>>()
    val searchMoviesLiveData: LiveData<List<MovieDto>> get() = _searchMoviesLiveData

    fun fetchMovies() {
        viewModelScope.launch {
            val movies = movieRepository.fetchMovies(
                page = 1
            )
            movies?.let {
                _moviesLiveData.postValue(it)
            }
        }
    }

    fun searchMovies(movieTitle: String) {
        viewModelScope.launch {
            val searchedMovies = movieRepository.searchMovies(movieTitle)
            searchedMovies?.let {
                _searchMoviesLiveData.postValue(it)
            }
        }
    }
}
