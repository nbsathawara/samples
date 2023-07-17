package com.example.demoproject.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.EXTRA_PLAYER
import com.example.demoproject.EXTRA_SKILL
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityFinishBinding
import com.example.demoproject.databinding.ActivitySkillBinding
import com.example.demoproject.model.Player

class FinishActivity : BaseActivity() {

    lateinit var binding: ActivityFinishBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val player = intent.getParcelableExtra<Player>(EXTRA_PLAYER)!!

        binding.txtSearch.text = "Looking for a ${player.league} ${player.skill} league near you..."


    }
}