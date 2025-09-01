package com.nbs.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityHomeBinding
import com.nbs.tmdbclient.presentation.artist.ArtistsActivity
import com.nbs.tmdbclient.presentation.movie.MoviesActivity
import com.nbs.tmdbclient.presentation.tvshow.TvShowsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        binding.apply {
            movieButton.setOnClickListener {
                startActivity(Intent(this@HomeActivity, MoviesActivity::class.java))
            }
            tvButton.setOnClickListener {
                startActivity(Intent(this@HomeActivity, TvShowsActivity::class.java))
            }
            artistsButton.setOnClickListener {
                startActivity(Intent(this@HomeActivity, ArtistsActivity::class.java))
            }
        }
    }
}