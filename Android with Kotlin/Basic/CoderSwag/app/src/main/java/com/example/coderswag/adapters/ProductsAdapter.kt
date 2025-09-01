package com.example.coderswag.adapters

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.coderswag.R
import com.example.coderswag.model.Product

class ProductsAdapter(val context: Context, val products: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {


    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_prod_grid, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindProduct(context, products[position])
    }

    inner class ProductHolder(itemView: View) : ViewHolder(itemView) {
        val imgProd = itemView.findViewById<ImageView>(R.id.imgProd)
        val titleProd = itemView.findViewById<TextView>(R.id.titleProd)
        val priceProd = itemView.findViewById<TextView>(R.id.priceProd)

        fun bindProduct(context: Context, product: Product) {
            imgProd.setImageResource(
                context.resources.getIdentifier(
                    product.image, "drawable",
                    context.packageName
                )
            )
            titleProd.text = product.title
            priceProd.text = product.price
        }

    }
}