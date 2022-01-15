package com.example.moviesdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.db.RoomMoviesDataSource
import com.example.moviesdb.network.NetworkMoviesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val PAGE_RESPONSE_SIZE = 20

class MoviesListViewModel(application: Application) : AndroidViewModel(application) {
    private var pageNumber = 0

    private val roomMoviesDataSource by lazy {
        RoomMoviesDataSource(application)
    }

    private val networkMoviesDataSource by lazy {
        NetworkMoviesDataSource()
    }

    private val _moviesListDB = MutableLiveData<List<MovieT>>()
    val moviesListDB: LiveData<List<MovieT>>
        get() = _moviesListDB

    init {
        getAllMoviesFromDB()
    }

    private fun getAllMoviesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesDB = roomMoviesDataSource.getAllMovies()
            withContext(Dispatchers.Main) {
                pageNumber = (moviesDB.size / PAGE_RESPONSE_SIZE) + 1
                _moviesListDB.value = moviesDB
            }
        }
    }

    // TODO when favorite * * * is selected
    fun getFavoriteMoviesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesDB = roomMoviesDataSource.getFavoriteMovies()
            withContext(Dispatchers.Main) {
                _moviesListDB.value = moviesDB
            }
        }
    }

    // TODO Call when size is ZERO || Scroll for new data
    fun getMoviesFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val moviesApi = networkMoviesDataSource.getMoviesFromApi(pageNumber)
            roomMoviesDataSource.insertMovies(moviesApi)
            getAllMoviesFromDB()
        }
    }
    // TODO Add TO Favorites + + +
    fun addToFavorite(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            roomMoviesDataSource.addToFavorite(movieId)
        }
    }
    // TODO Remove From Favorites - - -
    fun removeFromFavorite(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            roomMoviesDataSource.removeFromFavorite(movieId)
        }
    }

}