package com.example.moviesdb.views.movie_details

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesdb.R

private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"

@BindingAdapter("imageUrlDetails")
fun bindImage(imageView: ImageView, posterPath: String?) {
    posterPath?.let {
        val imageUri = BASE_POSTER_URL+it
        Glide.with(imageView.context)
            .applyDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.movie_place_holder).error(R.drawable.ic_broken_image)
            )
            .load(imageUri)
            .transition(GenericTransitionOptions.with(R.anim.left_slide_glide))
            .into(imageView)
    }
}

@BindingAdapter("isFavoriteMovie")
fun bindImageIsFavoriteMovie(imageView: ImageView, i: Int?) {
    when(i) {
        0 -> imageView.setImageResource(R.drawable.ic_star_off)
        1 -> imageView.setImageResource(R.drawable.ic_star_on)
    }
}