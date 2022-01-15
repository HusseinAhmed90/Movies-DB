package com.example.moviesdb.core.use_cases

import com.example.moviesdb.core.data.MovieT

interface MoviesDBDataSource {

    suspend fun insertMovies(movies: List<MovieT>)

    suspend fun getAllMovies(): List<MovieT>

    suspend fun getFavoriteMovies(): List<MovieT>

    suspend fun addToFavorite(movieId: Long)

    suspend fun removeFromFavorite(movieId: Long)

}