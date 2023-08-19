package com.nbs.tmdbclient.presentation.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbs.tmdbclient.R
import com.nbs.tmdbclient.custom.Utils
import com.nbs.tmdbclient.data.model.movie.Movie
import com.nbs.tmdbclient.databinding.CellListItemBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    val movies = ArrayList<Movie>()
    fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun getItemCount(): Int {
        return movies.size
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
        holder.bind(movies[position])
    }

    class ViewHolder(val binding: CellListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvDesc.text = movie.overview

            val imgUrl = Utils.imgBaseUrl + movie.posterPath
            Glide.with(binding.iv.context)
                .load(imgUrl)
                .into(binding.iv)

        }
    }

}