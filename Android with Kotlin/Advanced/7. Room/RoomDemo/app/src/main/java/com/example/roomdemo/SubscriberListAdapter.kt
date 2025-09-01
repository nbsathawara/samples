package com.example.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.CelllSubscriberListBinding
import com.example.roomdemo.db.Subscriber

class SubscriberListAdapter(
    private val itemClick: (Subscriber) -> Unit
) :
    RecyclerView.Adapter<SubscriberListAdapter.ViewHolder>() {

    private val subscribers = ArrayList<Subscriber>()

    fun setSubscribers(list: List<Subscriber>) {
        subscribers.clear()
        subscribers.addAll(list)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CelllSubscriberListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.celll_subscriber_list,
            parent,
            false
        )
        return ViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subscribers[position])
    }

    class ViewHolder(
        private val binding: CelllSubscriberListBinding,
        private val itemClick: (Subscriber) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subscriber: Subscriber) {
            binding.txtName.text = subscriber.name
            binding.txtEmail.text = subscriber.email

            binding.container.setOnClickListener {
                itemClick(subscriber)
            }
        }
    }
}