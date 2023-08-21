package com.nbs.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityArtistsBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtistsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding

    @Inject
    lateinit var artistViewModelFactory: ArtistViewModel.ArtistViewModelFactory
    private val artistViewModel: ArtistViewModel by viewModels { artistViewModelFactory }

    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artists)

        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.actionUpdate -> {
                updateArtists()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.recyclerView.adapter = adapter
        displayPopularArtists()
    }

    private fun displayPopularArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = artistViewModel.getArtists()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setArtists(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No data available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun updateArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = artistViewModel.updateArtists()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setArtists(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No updates available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }
}