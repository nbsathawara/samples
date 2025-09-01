package com.nbs.tmdbclient.presentation.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.model.artist.Artist
import com.nbs.tmdbclient.databinding.CellListItemBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    val artists = ArrayList<Artist>()
    fun setArtists(artists: List<Artist>) {
        this.artists.clear()
        this.artists.addAll(artists)
    }

    override fun getItemCount(): Int {
        return artists.size
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
        holder.bind(artists[position])
    }

    class ViewHolder(val binding: CellListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(artist: Artist) {
            binding.tvTitle.text = artist.name
            binding.tvDesc.text = artist.popularity.toString()

            val imgUrl = Utils.imgBaseUrl + artist.profilePath
            Glide.with(binding.iv.context)
                .load(imgUrl)
                .into(binding.iv)

        }
    }

}