package com.example.coderswag.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coderswag.R
import com.example.coderswag.model.Category

class CategoryRecycleAdapter(
    private val context: Context,
    private val categories: List<Category>,
    val itemClick: (Category) -> Unit
) :
    RecyclerView.Adapter<CategoryRecycleAdapter.Holder>() {

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_cat_list, parent, false)
        return Holder(view, itemClick)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindCategory(context, categories[position])
    }

    inner class Holder(itemView: View, val itemClick: (Category) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val catImg = itemView.findViewById<ImageView>(R.id.imgCat)
        val catName = itemView.findViewById<TextView>(R.id.nameCat)

        fun bindCategory(context: Context, category: Category) {
            catName.text = category.title
            catImg.setImageResource(
                context.resources.getIdentifier(
                    category.image,
                    "drawable",
                    context.packageName
                )
            )
            itemView.setOnClickListener { itemClick(category) }
        }
    }
}