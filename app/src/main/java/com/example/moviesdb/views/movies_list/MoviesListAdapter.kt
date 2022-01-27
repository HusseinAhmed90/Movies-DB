package com.example.moviesdb.views.movies_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.databinding.MovieListItemBinding

class MoviesListAdapter(private val listener: MovieItemListener):
    ListAdapter<MovieT, MoviesListAdapter.MovieViewHolder>(MovieDiffUtilCallBack) {

    class MovieItemListener(val itemClickListener: (movie: MovieT) -> Unit) {
        fun onClick(movie: MovieT) = itemClickListener(movie)
    }

    class MovieViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieT, clickListener: MovieItemListener) {
            binding.movie = movie
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object MovieDiffUtilCallBack : DiffUtil.ItemCallback<MovieT>() {
        override fun areItemsTheSame(oldItem: MovieT, newItem: MovieT): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieT, newItem: MovieT): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }
}