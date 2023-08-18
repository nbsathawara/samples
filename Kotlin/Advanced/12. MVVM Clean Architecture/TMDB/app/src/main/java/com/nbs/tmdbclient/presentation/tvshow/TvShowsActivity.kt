package com.nbs.tmdbclient.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityTvShowsBinding

class TvShowsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvShowsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_shows)
    }
}