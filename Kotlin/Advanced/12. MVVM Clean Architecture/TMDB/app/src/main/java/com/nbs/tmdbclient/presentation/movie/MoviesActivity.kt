package com.nbs.tmdbclient.presentation.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityMoviesBinding
import com.nbs.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var adapter: MovieAdapter

    @Inject
    lateinit var movieViewModelFactory: MovieViewModel.MovieViewModelFactory
    private val movieViewModel: MovieViewModel by viewModels { movieViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies)

        (application as Injector).createMovieSubComponent()
            .inject(this)
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.actionUpdate -> {
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.recyclerView.adapter = adapter
        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = movieViewModel.getMovies()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No data available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun updateMovies() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = movieViewModel.updateMovies()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No updates available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }
}