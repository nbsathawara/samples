package com.example.coderswag.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.coderswag.R
import com.example.coderswag.databinding.CellCatListBinding
import com.example.coderswag.model.Category
import com.example.coderswag.serives.DataService

class CategoryAdapter(context: Context, categories: List<Category>) : BaseAdapter() {

    val context = context
    val categories = categories


    override fun getCount(): Int {
        return categories.count()
    }

    override fun getItem(position: Int): Any {
        return categories.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var catView: View
        var holder = ViewHolder()
        if (convertView == null) {
            catView = LayoutInflater.from(context).inflate(R.layout.cell_cat_list, null)
            holder.catImag = catView.findViewById(R.id.imgCat)
            holder.nameCat = catView.findViewById(R.id.nameCat)
            catView.tag = holder
        } else {
            holder = convertView?.tag as ViewHolder
            catView = convertView
        }
        val category = categories[position]
        holder.nameCat?.text = category.title
        holder.catImag?.setImageResource(
            context.resources.getIdentifier(
                category.image,
                "drawable", context.packageName
            )
        )
        return catView
    }

    private class ViewHolder() {
        var catImag: ImageView? = null
        var nameCat: TextView? = null
    }
}