package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val subscriberViewModel: SubscriberViewModel by viewModels { subscriberViewModelFactory }
    private lateinit var subscriberViewModelFactory: SubscriberViewModel.SubscriberViewModelFactory
    private lateinit var adapter: SubscriberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDao()
        val repository = SubscriberRepository(dao)
        subscriberViewModelFactory = SubscriberViewModel.SubscriberViewModelFactory(repository)

        binding.lifecycleOwner = this
        binding.viewModel = subscriberViewModel
        initRecyclerView()
        subscriberViewModel.statusMsg.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        adapter = SubscriberListAdapter() { subscriber: Subscriber ->
            subscriberSelected(subscriber)
        }
        binding.rvSubscribers.adapter = adapter
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        binding.rvSubscribers.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        displaySubscribers()
    }

    private fun displaySubscribers() {

        subscriberViewModel.subscribers.observe(this, Observer { subscribers ->
            adapter.setSubscribers(subscribers)
            adapter.notifyDataSetChanged()
        })
    }

    private fun subscriberSelected(subscriber: Subscriber) {
        subscriberViewModel.subscriberSelected(subscriber)
    }
}