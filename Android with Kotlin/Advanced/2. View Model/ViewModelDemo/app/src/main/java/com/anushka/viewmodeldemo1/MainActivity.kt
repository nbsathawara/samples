package com.anushka.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.anushka.viewmodeldemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private lateinit var viewModelFactory: MainActivityViewModel.MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModel.MainActivityViewModelFactory(111)

        binding.apply {
            tvTotal.text = viewModel.getTotal().toString()
            btnAdd.setOnClickListener {
                viewModel.add(etNumber.text.toString().toIntOrNull())
                tvTotal.text = viewModel.getTotal().toString()
            }
        }
    }
}