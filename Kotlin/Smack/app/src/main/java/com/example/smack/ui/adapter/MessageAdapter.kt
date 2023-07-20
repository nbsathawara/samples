package com.example.smack.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smack.R
import com.example.smack.ui.model.Message
import com.example.smack.ui.service.UserDataService

class MessageAdapter(val context: Context, val messages: ArrayList<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageHolder>() {


    override fun getItemCount(): Int {
        return messages.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cell_msg, parent, false)
        return MessageHolder(view)
    }


    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messages[position]
        holder.bind(context, message)
    }

    inner class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile = itemView.findViewById<ImageView>(R.id.imgUser)
        val txtUserName = itemView.findViewById<TextView>(R.id.txtUserName)
        val txtMsgTime = itemView.findViewById<TextView>(R.id.txtMsgTime)
        val txtMsg = itemView.findViewById<TextView>(R.id.txtMessage)

        fun bind(context: Context, message: Message) {
            txtUserName.text = message.userName
            txtMsgTime.text = message.timeStamp
            txtMsg.text = message.message
            imgProfile.setImageResource(
                context.resources.getIdentifier(
                    message.userAvatar,
                    "drawable",
                    context.packageName
                )
            )
            imgProfile.setBackgroundColor(UserDataService.returnAvatarColor(message.userAvatarColor))
        }

    }
}