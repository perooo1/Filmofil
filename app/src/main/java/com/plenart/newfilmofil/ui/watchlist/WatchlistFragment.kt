package com.plenart.newfilmofil.ui.watchlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.plenart.newfilmofil.R
import com.plenart.newfilmofil.databinding.FragmentMovieDetailsBinding
import com.plenart.newfilmofil.databinding.FragmentWatchlistBinding
import com.plenart.newfilmofil.presentation.WatchlistViewModel
import com.plenart.newfilmofil.ui.movies.OnMovieSelectedListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class WatchlistFragment : Fragment(), OnMovieSelectedListener {

    private lateinit var binding: FragmentWatchlistBinding
    private lateinit var watchlistAdapter: WatchlistAdapter
    private val viewModel: WatchlistViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchlistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        watchlistAdapter = WatchlistAdapter()
        watchlistAdapter.onMovieSelectedListener = this

        binding.recyclerWatchlist.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = watchlistAdapter
        }

    }

    private fun initObservers() {

        viewModel.movies.observe(viewLifecycleOwner){
            if(it != null && it.isNotEmpty()){
                watchlistAdapter.setMoviesWatchlist(it)
            }
        }

    }

    override fun onMovieSelected(id: Long) {
        Log.i("TOUCH", "touchy touchy watchlist id ${id.toString()}")
        val action = WatchlistFragmentDirections.actionWatchlistFragmentToMovieDetailsFragment(id)
        findNavController().navigate(action)
    }


}