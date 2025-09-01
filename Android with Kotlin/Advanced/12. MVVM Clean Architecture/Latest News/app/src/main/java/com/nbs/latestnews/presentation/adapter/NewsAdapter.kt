package com.nbs.latestnews.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nbs.latestnews.data.model.Article
import com.nbs.latestnews.databinding.CellNewsListBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    val differ = AsyncListDiffer(this, callback)

    private var onItemClick: ((Article) -> Unit)? = null

    fun setOnItemClick(listener: (Article) -> Unit) {
        onItemClick = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding =
            CellNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class NewsHolder(private val binding: CellNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDesc.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source?.name

            Glide.with(binding.ivArticleImg.context)
                .load(article.urlToImage)
                .into(binding.ivArticleImg)

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(article)
                }
            }
        }
    }

    object callback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}