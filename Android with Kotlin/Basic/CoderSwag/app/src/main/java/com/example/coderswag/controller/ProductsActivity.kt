package com.example.coderswag.controller

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.coderswag.adapters.ProductsAdapter
import com.example.coderswag.databinding.ActivityProductsBinding
import com.example.coderswag.serives.DataService
import com.example.coderswag.utilities.EXTRA_CATEGORY

class ProductsActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductsBinding
    lateinit var adapter: ProductsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        val category = intent.getStringExtra(EXTRA_CATEGORY)!!
        adapter = ProductsAdapter(this, DataService.getProducts(category))
        binding.prodGridView.adapter = adapter
        var spanCount = 2
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            spanCount = 3
        val layoutManager = GridLayoutManager(this, spanCount)
        binding.prodGridView.layoutManager = layoutManager

    }
}