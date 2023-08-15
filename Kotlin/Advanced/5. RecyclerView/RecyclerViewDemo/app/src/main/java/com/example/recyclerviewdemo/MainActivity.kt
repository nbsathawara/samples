package com.example.recyclerviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recyclerviewdemo.databinding.ActivityMainBinding
import java.util.Collections
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val persons = listOf<Person>(
            Person("Jenil", 19, "Male", Date(104, 7, 15)),
            Person("Nikhil", 33, "Male", Date(90, 6, 31)),
            Person("Yash", 26, "Male", Date(97, 5, 18)),
            Person("Vaishnavi", 22, "FeMale", Date(101, 9, 7)),
            Person("Devanshi", 21, "FeMale", Date(103, 4, 25)),
            Person(
                "Urvi", 15, "FeMale", Date(108, 3, 10),
            ),
            Person(
                "Hemangi", 32, "Female", Date(91, 9, 30),
            ),
        )
        Collections.sort(persons)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = RecyclerViewAdapter(persons) {
            listItemClicked(it)
        }
    }


    private fun listItemClicked(person: Person) {
        Toast.makeText(
            this, person.name + " : " + person.age
                    + " : " + person.gender + " : " + person.getBirthDate(), Toast.LENGTH_SHORT
        ).show()
    }
}