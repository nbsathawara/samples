package com.example.smack.ui.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.R
import com.example.smack.databinding.ActivityMainBinding
import com.example.smack.ui.SmackApp
import com.example.smack.ui.service.UserDataService
import com.example.smack.ui.utilities.USER_DATA_CHANGE

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
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        LocalBroadcastManager.getInstance(this).registerReceiver(
            userDataChangeReceiver,
            IntentFilter(USER_DATA_CHANGE)
        )
    }

    private val userDataChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (SmackApp.prefs.isLoggedIn) {
                binding.navDrawerHeaderInclude.txtUserName.text = UserDataService.name
                binding.navDrawerHeaderInclude.txtUserEmail.text = UserDataService.email
                binding.navDrawerHeaderInclude.imgUserProfile.setImageResource(
                    resources.getIdentifier(
                        UserDataService.avatarName, "drawable", packageName
                    )
                )
                binding.navDrawerHeaderInclude.imgUserProfile.setBackgroundColor(
                    UserDataService.returnAvatarColor(
                        UserDataService.avatarColor
                    )
                )
                binding.navDrawerHeaderInclude.btnLogIn.text = "Log Out"

            }
        }
    }


    fun logInClicked(view: View) {
        if (SmackApp.prefs.isLoggedIn) {
            UserDataService.logout()
            binding.navDrawerHeaderInclude.txtUserName.text = ""
            binding.navDrawerHeaderInclude.txtUserEmail.text = ""
            binding.navDrawerHeaderInclude.imgUserProfile.setImageResource(R.drawable.profiledefault)
            binding.navDrawerHeaderInclude.imgUserProfile.setBackgroundColor(Color.TRANSPARENT)
            binding.navDrawerHeaderInclude.btnLogIn.text = "Log in"
        } else
            startActivity(Intent(this, LogInActivity::class.java))
    }

    fun addChannelClicked(view: View) {

    }

    fun sendMessageClicked(view: View) {

    }
}