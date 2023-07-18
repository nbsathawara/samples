package com.example.coderswag.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coderswag.adapters.CategoryRecycleAdapter
import com.example.coderswag.databinding.ActivityMainBinding
import com.example.coderswag.serives.DataService

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CategoryRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    fun initialize() {
        val categories = DataService.categories
        adapter = CategoryRecycleAdapter(this, categories)
        binding.catListView.adapter = adapter

        val layoutManager=LinearLayoutManager(this)
        binding.catListView.layoutManager=layoutManager
       binding.catListView.setHasFixedSize(true)


    }
}