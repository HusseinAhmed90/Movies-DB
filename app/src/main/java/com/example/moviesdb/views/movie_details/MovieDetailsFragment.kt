package com.example.moviesdb.views.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.moviesdb.databinding.FragmentMovieDetailsBinding
import com.google.android.material.snackbar.Snackbar

class MovieDetailsFragment : Fragment() {


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        binding.movie = movie
        viewModel.isFavoriteMovie(movie.isFavorite)
        binding.viewModel = viewModel
        binding.doneRatingBtn.isEnabled = false

        binding.ratingBar.setOnRatingBarChangeListener { _, _, _ ->
            binding.doneRatingBtn.isEnabled = true
        }

        binding.doneRatingBtn.setOnClickListener {
            binding.doneRatingBtn.isEnabled = false
            val rate = binding.ratingBar.rating
            viewModel.rateMovie(movie.id, rate)
        }

        viewModel.rate.observe(viewLifecycleOwner, Observer {
            binding.ratingBar.rating = it
            showShowSnackBar("Movie rate Updated: $it")
        })

        binding.isFavoriteIv.setOnClickListener {
            when(viewModel.isFavorite.value) {
                0 -> {
                    viewModel.addToFavorite(movie.id)
                    showShowSnackBar("The movie is add to favorites")
                }
                1 -> {
                    viewModel.removeFromFavorite(movie.id)
                    showShowSnackBar("The movie is removed from favorites")
                }
            }
        }

    }

    @SuppressLint("ShowToast")
    private fun showShowSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }

}