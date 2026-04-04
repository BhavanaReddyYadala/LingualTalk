package com.example.primetalkerui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prime = findViewById<TextView>(R.id.tvPrime)
        val talker = findViewById<TextView>(R.id.tvTalker)
        val button = findViewById<MaterialButton>(R.id.btnContinue)

        button.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Animation
        prime.animate().alpha(1f).setDuration(800).start()

        talker.postDelayed({
            talker.animate().alpha(1f).setDuration(600).start()
        }, 400)
    }
}