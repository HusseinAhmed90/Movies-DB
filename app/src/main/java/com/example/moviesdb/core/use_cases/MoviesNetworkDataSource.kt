package com.example.moviesdb.core.use_cases

import com.example.moviesdb.core.data.MovieT

interface MoviesNetworkDataSource {

    suspend fun getMoviesFromApi(page: Int): List<MovieT>

}