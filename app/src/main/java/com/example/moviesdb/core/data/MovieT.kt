package com.example.moviesdb.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieT(
    @PrimaryKey
    val id: Long,
    val title: String,
    val overview: String,
    val poster_path: String,
    val isFavorite: Int = 0
)