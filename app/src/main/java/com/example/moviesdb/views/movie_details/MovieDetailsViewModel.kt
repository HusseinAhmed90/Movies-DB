package com.example.moviesdb.views.movie_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.db.RoomMoviesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsViewModel(application: Application): AndroidViewModel(application) {

    private val roomMoviesDataSource by lazy {
        RoomMoviesDataSource(application)
    }

    private val _rate = MutableLiveData<Float>()
    val rate: LiveData<Float>
        get() = _rate

    private val _isFavorite = MutableLiveData<Int>()
    val isFavorite: LiveData<Int>
        get() = _isFavorite

    fun isFavoriteMovie(i: Int) {
        _isFavorite.value = i
    }

    fun rateMovie(movieId: Long, rate: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            roomMoviesDataSource.updateMovieRate(movieId, rate)
            withContext(Dispatchers.Main) {
                _rate.value = rate
            }
        }
    }

    fun addToFavorite(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            roomMoviesDataSource.addToFavorite(movieId)
            withContext(Dispatchers.Main){
                _isFavorite.value = 1
            }
        }
    }

    fun removeFromFavorite(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            roomMoviesDataSource.removeFromFavorite(movieId)
            withContext(Dispatchers.Main){
                _isFavorite.value = 0
            }
        }
    }
}