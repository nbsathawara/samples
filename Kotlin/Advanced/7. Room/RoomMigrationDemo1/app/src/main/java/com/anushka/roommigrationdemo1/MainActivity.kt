package com.anushka.roommigrationdemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.anushka.roommigrationdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = StudentDatabase.getInstance(application).subscriberDAO

        dao.getAllStudents().observe(this, Observer { students ->
            Log.i("My Tag", "Students : ${students.toString()}")
        })

        binding.apply {
            btnSubmit.setOnClickListener {
                lifecycleScope.launch {
                    etName.text.let {
                        dao.insertStudent(
                            Student(
                                0,
                                it.toString(),
                                etEmail.text.toString(),
                                (etAge.text.toString()).toInt(),
                                etCourse.text.toString()
                            )
                        )
                        etName.setText("")
                        etEmail.setText("")
                        etAge.setText("")
                        etCourse.setText("")
                    }
                }
            }
        }


    }
}