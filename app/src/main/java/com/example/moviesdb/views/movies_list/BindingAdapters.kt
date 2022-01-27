package com.example.moviesdb.views

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.view_model.LoadingStatus
import com.example.moviesdb.R

private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("listData")
fun submitListToRV(rv: RecyclerView, list: List<MovieT>) {
    val adapter = rv.adapter as MoviesListAdapter
    adapter.submitList(list)
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, posterPath: String?) {
    posterPath?.let {
        val imageUri = BASE_POSTER_URL+it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .applyDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.movie_place_holder).error(R.drawable.ic_broken_image)
            )
            .load(imageUri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}
//
//@BindingAdapter("loadingStatus")
//fun statusLoaded(imageView: ImageView, loadingStatus: LoadingStatus) {
//    when (loadingStatus) {
//        LoadingStatus.LOADING -> {
//            imageView.visibility = View.VISIBLE
//            imageView.setImageResource(R.drawable.loading_animation)
//        }
//        LoadingStatus.DONE -> {
//            imageView.visibility = View.GONE
//        }
//        LoadingStatus.ERROR -> {
//            imageView.visibility = View.VISIBLE
//            imageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
//        }
//    }
//}