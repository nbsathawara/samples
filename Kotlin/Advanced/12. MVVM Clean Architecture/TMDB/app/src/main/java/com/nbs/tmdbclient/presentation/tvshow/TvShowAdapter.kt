package com.nbs.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.model.tvshow.TvShow
import com.nbs.tmdbclient.databinding.CellListItemBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    val tvShows = ArrayList<TvShow>()
    fun setTvShows(tvShows: List<TvShow>) {
        this.tvShows.clear()
        this.tvShows.addAll(tvShows)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CellListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cell_list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    class ViewHolder(val binding: CellListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.tvTitle.text = tvShow.name
            binding.tvDesc.text = tvShow.overview

            val imgUrl = Utils.imgBaseUrl + tvShow.posterPath
            Glide.with(binding.iv.context)
                .load(imgUrl)
                .into(binding.iv)

        }
    }

}