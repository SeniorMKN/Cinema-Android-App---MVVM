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

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    private var currentPage = 1

    fun fetchMovies(isPageScrolled: Boolean = false) {
        viewModelScope.launch {
            val movies = movieRepository.fetchMovies(page = if (isPageScrolled) currentPage else 1)
            movies?.let {
                if (isPageScrolled) {
                    val currentMoviesList = _moviesLiveData.value ?: emptyList()
                    _moviesLiveData.postValue(currentMoviesList + it)
                } else {
                    _moviesLiveData.postValue(it)
                }
                currentPage++
            }
            _loadingState.value = false
        }
    }

    fun searchMovies(movieTitle: String) {
        viewModelScope.launch {
            val searchedMovies = movieRepository.searchMovies(movieTitle)
            searchedMovies?.let {
                _searchMoviesLiveData.postValue(it)
            }
            _loadingState.value = false
        }
    }
}
