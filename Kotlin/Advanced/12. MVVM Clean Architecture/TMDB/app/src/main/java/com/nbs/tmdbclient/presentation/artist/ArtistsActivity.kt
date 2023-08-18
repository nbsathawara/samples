package com.nbs.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.databinding.ActivityArtistsBinding

class ArtistsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtistsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artists)
    }
}