package com.example.demoproject.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.databinding.ActivityLeagueBinding

class LeagueActivity : BaseActivity() {

    lateinit var binding: ActivityLeagueBinding

    val LEAUGUE_MENS = 1
    val LEAUGUE_WOMENS = 2
    val LEAUGUE_COED = 3
    var selectedLeaugue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun toggleClicked(view: View) {

        binding.btnLeagueMens.isChecked = false
        binding.btnLeaugueWomens.isChecked = false
        binding.btnLeaugueCoed.isChecked = false

        val tag = (view.tag.toString()).toInt()
        when (tag) {
            LEAUGUE_MENS -> {
                selectedLeaugue = "mens"
                binding.btnLeagueMens.isChecked = true
            }
            LEAUGUE_WOMENS -> {
                selectedLeaugue = "womens"
                binding.btnLeaugueWomens.isChecked = true
            }
            LEAUGUE_COED -> {
                selectedLeaugue = "coed"
                binding.btnLeaugueCoed.isChecked = true
            }
        }
    }

    fun nextClicked(view: View) {
        if (selectedLeaugue.isNullOrEmpty())
            Toast.makeText(this, "Please select a league", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(this, SkillActivity::class.java)
            intent.putExtra(EXTRA_LEAGUE, selectedLeaugue)
            startActivity(intent)
        }
    }
}