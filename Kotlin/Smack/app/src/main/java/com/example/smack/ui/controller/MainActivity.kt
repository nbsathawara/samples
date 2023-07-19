package com.example.smack.ui.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.smack.R
import com.example.smack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navDrawerHeaderInclude.btnLogIn.setOnClickListener {
            logInClicked(it)
        }

        binding.navDrawerHeaderInclude.btnAddChannel.setOnClickListener {
            addChannelClicked(it)
        }

        binding.appBarMain.mainContent.btnSend.setOnClickListener {
            logInClicked(it)
        }

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    } 

    fun logInClicked(view: View) {
        startActivity(Intent(this, LogInActivity::class.java))
    }

    fun addChannelClicked(view: View) {

    }

    fun sendMessageClicked(view: View) {

    }
}