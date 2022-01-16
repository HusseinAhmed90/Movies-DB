package com.example.moviesdb.core.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movies_table")
@Parcelize
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
): Parcelable