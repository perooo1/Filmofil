package com.plenart.newfilmofil.ui.movies.movie_details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.plenart.newfilmofil.R
import com.plenart.newfilmofil.databinding.FragmentMovieDetailsBinding
import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.presentation.MovieDetailsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.util.*
import kotlin.concurrent.timerTask


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val viewModel: MovieDetailsViewModel by viewModel { parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val m = viewModel.getMovieDetails(args.movieId)
            display(m)
        }
    }

    private fun display(movie: MovieDetails?) {
        movie.let {
            val imgURL = "https://image.tmdb.org/t/p/w342" + movie!!.posterPath
            binding.apply {
                Glide.with(requireContext()).load(imgURL).into(ivMovieImagePoster)
                tvMovieTitle.text = movie!!.title
                tvMovieYearReleased.text = movie.releaseDate
                tvMovieBudget.text = movie.budget.toString()
                tvMovieOverview.text = movie.overview
                tvMovieStatus.text = movie.status
            }
        }
    }

}