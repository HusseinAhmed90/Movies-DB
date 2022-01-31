package com.example.moviesdb.db

import android.content.Context
import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.core.use_cases.MoviesDBDataSource

class RoomMoviesDataSource(context: Context): MoviesDBDataSource {

    private val movieDao = DatabaseService.getInstance(context).movieDao()

    override suspend fun insertMovies(movies: List<MovieT>) = movieDao.insertMovies(movies)

    override suspend fun getAllMovies(): List<MovieT> = movieDao.getAllMovies()

    override suspend fun getFavoriteMovies(): List<MovieT> = movieDao.getFavoriteMovies()

    override suspend fun addToFavorite(movieId: Long) = movieDao.addToFavorite(movieId)

    override suspend fun removeFromFavorite(movieId: Long) = movieDao.removeFromFavorite(movieId)

    override suspend fun updateMovieRate(movieId: Long, rate: Float) = movieDao.updateMovieRate(movieId, rate)

}