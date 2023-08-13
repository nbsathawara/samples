package com.example.demoproject.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.EXTRA_PLAYER
import com.example.demoproject.EXTRA_SKILL
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityLeagueBinding
import com.example.demoproject.databinding.ActivitySkillBinding
import com.example.demoproject.model.Player

class SkillActivity : BaseActivity() {
    lateinit var player: Player

    lateinit var binding: ActivitySkillBinding

    val SKILL_BEGINNER = 1
    val SKILL_BALLER = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        player = intent.getParcelableExtra(EXTRA_PLAYER)!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(EXTRA_PLAYER, player)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null)
            player = savedInstanceState.getParcelable(EXTRA_PLAYER)!!
    }


    fun toggleClicked(view: View) {
        binding.btnBeginner.isChecked = false
        binding.btnBaller.isChecked = false

        val tag = (view.tag.toString()).toInt()
        when (tag) {
            SKILL_BEGINNER -> {
                player.skill = "beginner"
                binding.btnBeginner.isChecked = true
            }
            SKILL_BALLER -> {
                player.skill = "baller"
                binding.btnBaller.isChecked = true
            }
        }
    }

    fun finishClicked(view: View) {
        if (player.skill.isNullOrEmpty())
            Toast.makeText(this, "Please select a skill", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(this, FinishActivity::class.java)
            intent.putExtra(EXTRA_PLAYER, player)
            startActivity(intent)
        }
    }

}