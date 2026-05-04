package com.example.primetalkerui

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // 🔙 Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            overridePendingTransition(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }

        // 👋 Greeting
        val tvHello = findViewById<TextView>(R.id.tvHello)
        val name = intent.getStringExtra("NAME") ?: "User"
        tvHello.text = "Hello $name 👋"

        // 🔥 Nav Buttons
        val home = findViewById<ImageView>(R.id.navHome)
        val call = findViewById<ImageView>(R.id.navCall)
        val video = findViewById<ImageView>(R.id.navVideo)
        val message = findViewById<ImageView>(R.id.navMessage)
        val settings = findViewById<ImageView>(R.id.navSettings)

        val buttons = listOf(home, call, video, message, settings)

        fun selectButton(selected: ImageView) {
            for (btn in buttons) {
                btn.setBackgroundResource(R.drawable.nav_circle)
            }
            selected.setBackgroundResource(R.drawable.nav_circle_activate)

            val anim = AnimationUtils.loadAnimation(this, R.anim.nav_click)
            selected.startAnimation(anim)
        }

        // 🔥 Load Fragment Function
        fun loadFragment(fragment: Fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }

        // ✅ Default Screen
        loadFragment(HomeFragment())
        selectButton(home)

        // 🏠 HOME
        home.setOnClickListener {
            selectButton(home)
            loadFragment(HomeFragment())
        }

        // 📞 CALL
        call.setOnClickListener {
            selectButton(call)
            loadFragment(CallFragment())
        }

        // 🎥 VIDEO
        video.setOnClickListener {
            selectButton(video)
            loadFragment(VideoFragment())
        }

        // 💬 MESSAGE
        message.setOnClickListener {
            selectButton(message)
            loadFragment(MessageFragment())
        }

        // ⚙️ SETTINGS
        settings.setOnClickListener {
            selectButton(settings)
            loadFragment(SettingsFragment())
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.slide_in_left,
            R.anim.slide_out_right
        )
    }
}