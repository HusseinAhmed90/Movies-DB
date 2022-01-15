package com.example.moviesdb.network

import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.core.use_cases.MoviesNetworkDataSource

class NetworkMoviesDataSource: MoviesNetworkDataSource {

    override suspend fun getMoviesFromApi(page: Int): List<MovieT> = MoviesApi.getMoviesFromApi(page)

}