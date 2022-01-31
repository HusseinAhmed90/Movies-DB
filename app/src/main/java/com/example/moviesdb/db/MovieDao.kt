package com.example.moviesdb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesdb.core.data.MovieT


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieT?>)

    @Query("SELECT * FROM movies_table")
    suspend fun getAllMovies(): List<MovieT>

    @Query("SELECT * FROM movies_table WHERE isFavorite = 1")
    suspend fun getFavoriteMovies(): List<MovieT>

    @Query("UPDATE movies_table SET rate = :rate WHERE id = :movieId")
    suspend fun updateMovieRate(movieId: Long, rate: Float)

    @Query("UPDATE movies_table SET isFavorite = 1 WHERE id = :movieId")
    suspend fun addToFavorite(movieId: Long)

    @Query("UPDATE movies_table SET isFavorite = 0 WHERE id = :movieId")
    suspend fun removeFromFavorite(movieId: Long)

}