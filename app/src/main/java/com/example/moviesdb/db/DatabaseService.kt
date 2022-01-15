package com.example.moviesdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesdb.core.data.MovieT

@Database(entities = [MovieT::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    companion object {

        private const val DB_NAME = "movies_t.db"
        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context).also { instance = it })

    }

    abstract fun movieDao(): MovieDao

}