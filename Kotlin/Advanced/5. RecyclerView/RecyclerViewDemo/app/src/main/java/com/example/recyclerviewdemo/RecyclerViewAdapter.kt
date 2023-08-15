package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val persons: List<Person>,
    private val itemClick: (Person) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return persons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.cell_recycler_view, parent, false)
        return ViewHolder(cell)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(persons[position], itemClick)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(person: Person, itemClick: (Person) -> Unit) {
            val tvName = view.findViewById<TextView>(R.id.tvName)
            tvName.text = person.name
            view.setOnClickListener {
                itemClick(person)
            }

//            view.setOnClickListener {
//                Toast.makeText(
//                    view.context, person.name + " : " + person.age
//                            + " : " + person.gender + " : " + person.getBirthDate(), Toast.LENGTH_SHORT
//                ).show()
//            }
        }
    }

}