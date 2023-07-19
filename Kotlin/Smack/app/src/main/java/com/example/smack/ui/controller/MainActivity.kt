package com.example.smack.ui.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.ui.AppBarConfiguration
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.smack.R
import com.example.smack.databinding.ActivityMainBinding
import com.example.smack.ui.SmackApp
import com.example.smack.ui.model.Channel
import com.example.smack.ui.service.MessageService
import com.example.smack.ui.service.UserDataService
import com.example.smack.ui.utilities.*
import io.socket.client.IO
import io.socket.emitter.Emitter
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var channelAdapter: ArrayAdapter<Channel>

    val socket = IO.socket(SOCKET_URL)
    val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarMain.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        initialize()
    }

    fun initialize() {

        binding.navDrawerHeaderInclude.btnLogIn.setOnClickListener {
            logInClicked(it)
        }

        binding.navDrawerHeaderInclude.btnAddChannel.setOnClickListener {
            addChannelClicked(it)
        }

        binding.appBarMain.mainContent.btnSend.setOnClickListener {
            sendMessageClicked(it)
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            userDataChangeReceiver,
            IntentFilter(USER_DATA_CHANGE)
        )

        LocalBroadcastManager.getInstance(this).registerReceiver(
            newChannelReceiver,
            IntentFilter(NEW_CHANNEL)
        )

        channelAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, MessageService.channels)
        binding.lvChannels.adapter = channelAdapter
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(userDataChangeReceiver, IntentFilter(USER_DATA_CHANGE))
        socket.connect()
        socket.on("channelCreated", onNewChannel)
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(userDataChangeReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(newChannelReceiver)
        socket.disconnect()
        super.onDestroy()
    }

    private val onNewChannel = Emitter.Listener { args ->
        val newChannel = Channel(args[0].toString(), args[1].toString(), args[2].toString())
        runOnUiThread {

        }
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

                MessageService.getChannels()
                channelAdapter.notifyDataSetChanged()
            }
        }
    }

    private val newChannelReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (SmackApp.prefs.isLoggedIn) {
                val args = intent?.getStringArrayExtra(NEW_CHANNEL)!!
                val newChannel = Channel(args[0].toString(), args[1].toString(), args[2].toString())
                MessageService.channels.add(newChannel)

                println("New Channel in Receiver : ${newChannel.name} : ${newChannel.desc} : ${newChannel.id}")
                MessageService.getChannels()
                channelAdapter.notifyDataSetChanged()

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
        if (SmackApp.prefs.isLoggedIn) {
            val builder = android.app.AlertDialog.Builder(this)
            val dialogView =
                LayoutInflater.from(this).inflate(R.layout.add_channel_dialog, null)
            builder.setView(dialogView)
                .setPositiveButton("Add") { dialog, i ->
                    val channelName =
                        dialogView.findViewById<EditText>(R.id.etChannelName).text.toString()
                    val channelDesc =
                        dialogView.findViewById<EditText>(R.id.etChannelDesc).text.toString()
                    hideKeyboard(this)

                    //Create channel with name & desc
                    val id = random.nextInt(10000).toString()
                    val channel = Channel(channelName, channelDesc, id)
                    socket.emit("newChannel", channel.name, channel.desc)
                    addChannel(channel)

                    val newChannelIntent = Intent(NEW_CHANNEL)
                    newChannelIntent.putExtra(NEW_CHANNEL, arrayOf(channelName, channelDesc, id))
                    LocalBroadcastManager.getInstance(this).sendBroadcast(newChannelIntent)
                }
                .setNegativeButton("Cancel") { dialog, i ->
                }
            builder.create().show()
        }
    }


    fun sendMessageClicked(view: View) {
        hideKeyboard(this)
    }
}