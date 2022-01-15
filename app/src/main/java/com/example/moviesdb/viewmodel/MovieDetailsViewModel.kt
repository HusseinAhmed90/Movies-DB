package com.example.moviesdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesdb.core.data.MovieT

class MovieDetailsViewModel: ViewModel() {

    private val _movieT = MutableLiveData<MovieT>()
    val movieT: LiveData<MovieT>
        get() = _movieT

    fun setMovie(movieT: MovieT) {
        _movieT.value = movieT
    }
}