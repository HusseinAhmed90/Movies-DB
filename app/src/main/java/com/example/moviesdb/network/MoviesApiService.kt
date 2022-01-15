package com.example.moviesdb.network

import com.example.moviesdb.core.data.MovieT
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"
private const val API_KEY = "ea6fa30a7cd5171aad434590ba0e66f8"
private const val LANGUAGE = "en-US"

interface MoviesApiService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): MoviesApiResponse
}

data class MoviesApiResponse (
    val page: Int,
    val results: List<MovieT>
)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

private val httpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BASIC)

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .build()

object MoviesApi {

    private val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
    
    suspend fun getMoviesFromApi(page: Int) = retrofitService.getPopularMovies(
        API_KEY, LANGUAGE, page
    ).results

}
