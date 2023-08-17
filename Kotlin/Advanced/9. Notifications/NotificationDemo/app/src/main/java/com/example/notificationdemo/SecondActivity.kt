package com.example.notificationdemo

import android.app.NotificationManager
import android.content.Context
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.example.notificationdemo.databinding.ActivityMainBinding
import com.example.notificationdemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput() {
        val replyKey = "key_reply"
        val remoteInput = RemoteInput.getResultsFromIntent(this.intent)
        if (remoteInput != null) {
            val input = remoteInput.getCharSequence(replyKey)
            binding.textView.text = input

            val channelId = "com.example.notificationdemo.channel1"
            val replyTd = 111
            val repliedNotification = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentText("Reply Received!!!")
                .build()

            with(NotificationManagerCompat.from(this))
            {
                notify(replyTd, repliedNotification)
            }
        }
    }

}