package com.example.primetalkerui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class MessageFragment : Fragment(R.layout.fragment_message) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.messageList)

        val names = listOf("Chintu", "Mom", "Chaitu", "Siri")
        val messages = listOf("Hi!", "Call me", "Let's meet", "Done 👍")
        val times = listOf("10:30", "9:15", "Yesterday", "Sat")

        val adapter = object : BaseAdapter() {

            override fun getCount() = names.size
            override fun getItem(position: Int) = null
            override fun getItemId(position: Int) = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView = layoutInflater.inflate(R.layout.item_message, parent, false)

                itemView.findViewById<TextView>(R.id.tvName).text = names[position]
                itemView.findViewById<TextView>(R.id.tvMessage).text = messages[position]
                itemView.findViewById<TextView>(R.id.tvTime).text = times[position]

                // ✅ SAME PROFILE ICON FOR ALL
                itemView.findViewById<ImageView>(R.id.imgProfile)
                    .setImageResource(R.drawable.profile)

                return itemView
            }
        }

        listView.adapter = adapter
    }
}