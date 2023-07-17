package com.example.demoproject.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.EXTRA_SKILL
import com.example.demoproject.R
import com.example.demoproject.databinding.ActivityLeagueBinding
import com.example.demoproject.databinding.ActivitySkillBinding

class SkillActivity : BaseActivity() {
    var league = ""

    lateinit var binding: ActivitySkillBinding

    val SKILL_BEGINNER = 1
    val SKILL_BALLER = 2
    var selectedSkill = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkillBinding.inflate(layoutInflater)
        setContentView(binding.root)

        league = intent.getStringExtra(EXTRA_LEAGUE)!!

    }

    fun toggleClicked(view: View) {
        binding.btnBeginner.isChecked = false
        binding.btnBaller.isChecked = false

        val tag = (view.tag.toString()).toInt()
        when (tag) {
            SKILL_BEGINNER -> {
                selectedSkill = "beginner"
                binding.btnBeginner.isChecked = true
            }
            SKILL_BALLER -> {
                selectedSkill = "baller"
                binding.btnBaller.isChecked = true
            }
        }
    }

    fun finishClicked(view: View) {
        if (selectedSkill.isNullOrEmpty())
            Toast.makeText(this, "Please select a skill", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(this, FinishActivity::class.java)
            intent.putExtra(EXTRA_LEAGUE, league)
            intent.putExtra(EXTRA_SKILL, selectedSkill)
            startActivity(intent)
        }
    }

}