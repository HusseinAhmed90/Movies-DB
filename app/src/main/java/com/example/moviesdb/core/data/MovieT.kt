package com.example.moviesdb.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieT(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Float,
    val popularity: Float,
    val poster_path: String,
    val rate: Float = 0.0f,
    val isFavorite: Int = 0
)