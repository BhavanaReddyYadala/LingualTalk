package com.example.lingualtalk

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<MaterialButton?>(R.id.btnContinue)

        button?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // 🔥 NAVIGATION
        val home = findViewById<ImageView?>(R.id.navHome)
        val call = findViewById<ImageView?>(R.id.navCall)
        val message = findViewById<ImageView?>(R.id.navMessage)
        val video = findViewById<ImageView?>(R.id.navVideo)
        val settings = findViewById<ImageView?>(R.id.navSettings)

        fun animate(view: View) {
            val anim = AnimationUtils.loadAnimation(this, R.anim.nav_click)
            view.startAnimation(anim)
        }

        home?.setOnClickListener { animate(home) }

        call?.setOnClickListener {
            animate(call)
            startActivity(Intent(Intent.ACTION_DIAL))
        }

        message?.setOnClickListener {
            animate(message)
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("sms:")))
        }

        video?.setOnClickListener {
            animate(video)
        }

        settings?.setOnClickListener {
            animate(settings)
        }
    }
}