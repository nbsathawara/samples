package com.nbs.tmdbclient.presentation.tvshow

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
import com.nbs.tmdbclient.databinding.ActivityTvShowsBinding
import com.nbs.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowsBinding

    @Inject
    lateinit var tvShowViewModelFactory: TvShowViewModel.TvShowViewModelFactory
    private val tvShowViewModel: TvShowViewModel by viewModels { tvShowViewModelFactory }

    private lateinit var adapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows)

        (application as Injector).createTvShowSubComponent()
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
                updateTvShows()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TvShowAdapter()
        binding.recyclerView.adapter = adapter
        displayPopularTvShows()
    }

    private fun displayPopularTvShows() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = tvShowViewModel.getTvShows()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setTvShows(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No data available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun updateTvShows() {
        binding.progressBar.visibility = View.VISIBLE
        val resLiveData = tvShowViewModel.updateTvShows()
        resLiveData.observe(this, Observer {
            if (it != null) {
                adapter.setTvShows(it)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(applicationContext, "No updates available...", Toast.LENGTH_SHORT)
                    .show()
            binding.progressBar.visibility = View.GONE
        })
    }
}