package com.example.demoproject.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.EXTRA_SKILL
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityFinishBinding
import com.example.demoproject.databinding.ActivitySkillBinding

class FinishActivity : BaseActivity() {

    lateinit var binding: ActivityFinishBinding
    var league = ""
    var skill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        league = intent.getStringExtra(EXTRA_LEAGUE)!!
        skill = intent.getStringExtra(EXTRA_SKILL)!!

        binding.txtSearch.text = "Looking for a $league $skill league near you..."


    }
}