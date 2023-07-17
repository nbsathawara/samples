package com.example.demoproject.controller

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.example.demoproject.EXTRA_LEAGUE
import com.example.demoproject.EXTRA_PLAYER
import com.example.demoproject.databinding.ActivityLeagueBinding
import com.example.demoproject.model.Player

class LeagueActivity : BaseActivity() {

    lateinit var binding: ActivityLeagueBinding
    var player = Player("", "")
    val LEAUGUE_MENS = 1
    val LEAUGUE_WOMENS = 2
    val LEAUGUE_COED = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        binding.btnLeagueMens.isChecked = false
        binding.btnLeaugueWomens.isChecked = false
        binding.btnLeaugueCoed.isChecked = false

        val tag = (view.tag.toString()).toInt()
        when (tag) {
            LEAUGUE_MENS -> {
                player.league = "mens"
                binding.btnLeagueMens.isChecked = true
            }
            LEAUGUE_WOMENS -> {
                player.league = "womens"
                binding.btnLeaugueWomens.isChecked = true
            }
            LEAUGUE_COED -> {
                player.league = "co-ed"
                binding.btnLeaugueCoed.isChecked = true
            }
        }
    }

    fun nextClicked(view: View) {
        if (player.league.isNullOrEmpty())
            Toast.makeText(this, "Please select a league", Toast.LENGTH_SHORT).show()
        else {
            val intent = Intent(this, SkillActivity::class.java)
            intent.putExtra(EXTRA_PLAYER, player)
            startActivity(intent)
        }
    }
}