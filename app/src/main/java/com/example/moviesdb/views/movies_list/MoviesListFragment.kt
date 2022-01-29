package com.example.moviesdb.views.movies_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesdb.core.data.MovieT
import com.example.moviesdb.databinding.FragmentMoviesListBinding

class MoviesListFragment : Fragment() {

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.moviesRv.apply {
            adapter = MoviesListAdapter(MoviesListAdapter.MovieItemListener {
                navigateToMovieDetailsFragment(it)
            })
        }
    }

    private fun navigateToMovieDetailsFragment(movie: MovieT) {
        findNavController().navigate(
            MoviesListFragmentDirections.toMovieDetailsFragment(movie)
        )
    }

}