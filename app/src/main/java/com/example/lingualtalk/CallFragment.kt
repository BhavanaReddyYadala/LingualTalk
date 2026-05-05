package com.example.lingualtalk

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class CallFragment : Fragment(R.layout.fragment_call) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.callList)

        val names = listOf("MOM", "Chintu", "Ksheeru", "Office")
        val types = listOf("📥 Incoming", "❌ Missed", "📤 Outgoing", "📥 Incoming")
        val times = listOf("10:30 AM", "9:15 AM", "Yesterday", "Sat")

        val adapter = object : BaseAdapter() {

            override fun getCount() = names.size
            override fun getItem(position: Int) = null
            override fun getItemId(position: Int) = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val itemView = layoutInflater.inflate(R.layout.item_call, parent, false)

                itemView.findViewById<TextView>(R.id.tvCallName).text = names[position]
                itemView.findViewById<TextView>(R.id.tvCallType).text = types[position]
                itemView.findViewById<TextView>(R.id.tvCallTime).text = times[position]

                // ✅ SAME PROFILE ICON
                itemView.findViewById<ImageView>(R.id.imgCallProfile)
                    .setImageResource(R.drawable.profile)

                return itemView
            }
        }

        listView.adapter = adapter
    }
}