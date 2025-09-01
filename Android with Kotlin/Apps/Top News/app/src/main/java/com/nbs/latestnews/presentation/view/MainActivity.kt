package com.nbs.latestnews.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nbs.latestnews.R
import com.nbs.latestnews.databinding.ActivityMainBinding
import com.nbs.latestnews.presentation.adapter.NewsAdapter
import com.nbs.latestnews.presentation.viewmodel.NewsViewModel
import com.nbs.latestnews.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var newsViewModelFactory: NewsViewModelFactory
    val newsViewModel: NewsViewModel by viewModels { newsViewModelFactory }

    @Inject
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
                .navController
        binding.bottomNav.setupWithNavController(controller)
    }
}