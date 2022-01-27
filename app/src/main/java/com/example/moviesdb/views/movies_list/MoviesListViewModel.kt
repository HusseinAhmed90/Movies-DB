package com.example.moviesdb.views.movies_list

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

enum class LoadingStatus { LOADING, DONE, ERROR}

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

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    init {
        getAllMoviesFromDB()
    }

    private fun getAllMoviesFromDB() {
        _loadingStatus.value = LoadingStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val moviesDB = roomMoviesDataSource.getAllMovies()
            withContext(Dispatchers.Main) {
                pageNumber = (moviesDB.size / PAGE_RESPONSE_SIZE) + 1
                _moviesListDB.value = moviesDB
                _loadingStatus.value = LoadingStatus.DONE
                if (moviesDB.isEmpty()) {
                    getMoviesFromApi()
                }
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

    //  Call when size is ZERO || TODO Scroll for new data
    private fun getMoviesFromApi() {
        _loadingStatus.value = LoadingStatus.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            val moviesApi = networkMoviesDataSource.getMoviesFromApi(pageNumber)
            roomMoviesDataSource.insertMovies(moviesApi)
            val moviesDB = roomMoviesDataSource.getAllMovies()
            withContext(Dispatchers.Main) {
                pageNumber = (moviesDB.size / PAGE_RESPONSE_SIZE) + 1
                _moviesListDB.value = moviesDB
                _loadingStatus.value = LoadingStatus.DONE
            }
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