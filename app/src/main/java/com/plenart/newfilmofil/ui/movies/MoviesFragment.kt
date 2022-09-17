package com.plenart.newfilmofil.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.plenart.newfilmofil.databinding.FragmentMoviesBinding
import com.plenart.newfilmofil.presentation.MoviesViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment(), OnMovieSelectedListener, SwipeRefreshLayout.OnRefreshListener {

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

        initObservers()
        initListeners()
        setupSearchView()
        setupRecyclerView()

    }

    private fun initListeners() {
        moviesFragmentBinding.fabWatchlist.setOnClickListener {
            val action = MoviesFragmentDirections.actionMoviesFragmentToWatchlistFragment()
            findNavController().navigate(action)
        }
    }

    private fun setupSearchView() {
        moviesFragmentBinding.searchView.isSubmitButtonEnabled = true

        moviesFragmentBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    if (query != null) {
                        viewModel.searchForMovie(query)
                    }
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return true
            }
        })
    }

    private fun initObservers() {
        viewModel.movies.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                moviesAdapter.setMovies(it)
            }
        }
        //for search
        viewModel.searchResults.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                moviesAdapter.setMovies(it)
            }
        }
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter()
        moviesAdapter.onMovieSelectedListener = this

        moviesFragmentBinding.recyclerMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = moviesAdapter


        }

        moviesFragmentBinding.swipeRefreshLayout.setOnRefreshListener(this)


    }

    override fun onMovieSelected(id: Long) {
        Log.i("TOUCH", "touchy touchy id ${id.toString()}")
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(id)
        findNavController().navigate(action)
    }

    override fun onRefresh() {
        Log.i("REFRESH", "IN REFRESH override")
        moviesAdapter.setMovies(viewModel.movies.value!!)
        viewModel.clearSearchedMovie()
        moviesFragmentBinding.swipeRefreshLayout.isRefreshing = false
    }

}