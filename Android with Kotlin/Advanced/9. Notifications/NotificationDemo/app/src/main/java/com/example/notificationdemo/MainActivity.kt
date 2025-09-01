package com.example.notificationdemo

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.example.notificationdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val channelId = "com.example.notificationdemo.channel1"
    private val replyKey = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        createNotificationChannel(channelId, "Channel Demo", "This is a demo")

        binding.button.setOnClickListener {
            if (checkForNotificationPermission())
                displayNotification()
        }
    }

    private fun displayNotification() {
        val notificationId = 111

        val tapIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, tapIntent, PendingIntent.FLAG_MUTABLE)

        //Reply action
        val remoteInput = RemoteInput.Builder(replyKey).run {
            setLabel("Enter your Name")
                .build()
        }
        val replyAction = NotificationCompat.Action.Builder(0, "REPLY", pendingIntent)
            .addRemoteInput(remoteInput).build()

        //action 1
        val intent1 = Intent(this, DetailsActivity::class.java)
        val pending1 =
            PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_IMMUTABLE)
        val action1 = NotificationCompat.Action.Builder(
            android.R.drawable.ic_menu_info_details,
            "Details",
            pending1
        ).build()

        //action 2
        val intent2 = Intent(this, SettingsActivity::class.java)
        val pending2 =
            PendingIntent.getActivity(this, 0, intent2, PendingIntent.FLAG_IMMUTABLE)
        val action2 = NotificationCompat.Action.Builder(
            android.R.drawable.sym_action_email,
            "Settings",
            pending2
        ).build()

        val notification = NotificationCompat.Builder(this@MainActivity, channelId)
            .setContentTitle("Sample title")
            .setContentText("Sample Description....")
            .setSmallIcon(android.R.drawable.ic_lock_power_off)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //.setContentIntent(pendingIntent)
            .addAction(action1)
            .addAction(action2)
            .addAction(replyAction)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, notification)
        }
    }

    private fun createNotificationChannel(id: String, name: String, desc: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                this.description = desc
            }

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun checkForNotificationPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf("android.permission.POST_NOTIFICATIONS")
            ActivityCompat.requestPermissions(
                this@MainActivity, permissions,
                1111
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        displayNotification()
    }
}