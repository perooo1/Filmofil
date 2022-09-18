package com.plenart.newfilmofil.ui.movies.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.plenart.newfilmofil.databinding.FragmentMovieDetailsBinding
import com.plenart.newfilmofil.models.MovieDetails
import com.plenart.newfilmofil.presentation.MovieDetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


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

        var m: MovieDetails? = null

        viewLifecycleOwner.lifecycleScope.launch {
            m = viewModel.getMovieDetails(args.movieId)
            display(m)
        }

        binding.btnAddToWatchlist.setOnClickListener {
            m?.let { it1 -> viewModel.saveMovie(it1) }
        }

    }

    private fun display(movie: MovieDetails?) {
        movie.let {
            val backdropURL = "https://image.tmdb.org/t/p/w1280" + movie!!.backdropPath
            binding.apply {
                Glide.with(requireContext()).load(backdropURL).centerCrop().into(ivMovieBackdrop)
                tvMovieTitle.text = movie!!.title
                tvMovieStatus.text = movie.status
                tvMovieYearReleased.text = movie.releaseDate
                ratingBar.numStars = calculateStarRating(movie.voteAverage)
                tvMovieOverview.text = movie.overview
                tvMovieBudget.text = movie.budget.toString()
                tvMovieRevenue.text = movie.revenue.toString()
            }
        }
    }

    private fun calculateStarRating(voteAvg: Double): Int {
        return when {
            voteAvg <= 5.0 -> 1
            voteAvg > 5.0 && voteAvg <= 6.5 -> 2
            voteAvg > 6.5 && voteAvg <= 7.5 -> 3
            voteAvg > 7.5 && voteAvg <= 8.4 -> 4
            else -> 5
        }
    }


}