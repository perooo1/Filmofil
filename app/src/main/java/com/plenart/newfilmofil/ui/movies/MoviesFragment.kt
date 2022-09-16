package com.plenart.newfilmofil.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.plenart.newfilmofil.R
import com.plenart.newfilmofil.databinding.FragmentMoviesBinding
import com.plenart.newfilmofil.presentation.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment(), OnMovieSelectedListener {

    private lateinit var moviesFragmentBinding: FragmentMoviesBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moviesFragmentBinding = FragmentMoviesBinding.inflate(inflater, container, false)
        return moviesFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO init observers, listeners, setup recycler
/*
        viewLifecycleOwner.lifecycleScope.launch {
            Log.i("TEST", "Movies fragmnet - inside lifecycle scope")
            viewModel.testFunction()
        }
*/
        initObservers()
        setupRecyclerView()

    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner){
            if(it != null && it.isNotEmpty()){
                moviesAdapter.setMovies(it)
            }
        }
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onMovieSelectedListener = this

        moviesFragmentBinding.recyclerMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = moviesAdapter


        }
    }

    override fun onMovieSelected(id: Long) {
        Log.i("TOUCH", "touchy touchy id ${id.toString()}")
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id)
        findNavController().navigate(action)
    }

}