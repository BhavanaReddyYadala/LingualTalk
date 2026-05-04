package com.example.primetalkerui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import android.text.Editable
import android.text.TextWatcher

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // 🔹 Views
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val radioMale = findViewById<RadioButton>(R.id.radioMale)
        val radioFemale = findViewById<RadioButton>(R.id.radioFemale)
        val spinner = findViewById<Spinner>(R.id.spLanguage)
        val btnContinue = findViewById<MaterialButton>(R.id.btnContinue)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvPercent = findViewById<TextView>(R.id.tvPercent)
        val neonBorder = findViewById<View>(R.id.neonBorder)

        // 🔥 Neon animation
        val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate)
        neonBorder.startAnimation(rotateAnim)

        // 🌐 Language list
        val languages = arrayOf(
            "Select Language",
            "తెలుగు", "हिन्दी", "தமிழ்", "ಕನ್ನಡ", "മലയാളം",
            "मराठी", "বাংলা", "ગુજરાતી", "ਪੰਜਾਬੀ", "اردو",
            "English", "Français", "Español", "Deutsch",
            "Português", "العربية", "日本語", "한국어",
            "中文", "Italiano"
        )

        val adapter = object : ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            languages
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }
        }

        spinner.adapter = adapter

        // 🔄 Progress Update
        fun updateProgress() {
            var progress = 0

            if (etName.text.toString().trim().isNotEmpty()) progress += 20
            if (etPhone.text.toString().trim().matches(Regex("^[0-9]{10}$"))) progress += 20
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString().trim()).matches()) progress += 20
            if (radioMale.isChecked || radioFemale.isChecked) progress += 20
            if (spinner.selectedItemPosition != 0) progress += 20

            progressBar.progress = progress
            tvPercent.text = "$progress%"
        }

        // 🔤 Live updates
        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) { updateProgress() }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        etName.addTextChangedListener(watcher)
        etPhone.addTextChangedListener(watcher)
        etEmail.addTextChangedListener(watcher)

        radioMale.setOnCheckedChangeListener { _, _ -> updateProgress() }
        radioFemale.setOnCheckedChangeListener { _, _ -> updateProgress() }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateProgress()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // 🚀 Continue Button
        btnContinue.setOnClickListener {

            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()

            // ✅ Name validation
            if (name.isEmpty()) {
                etName.error = "Enter name"
                etName.requestFocus()
                return@setOnClickListener
            }

            if (!name.matches(Regex("^[A-Za-z ]+$"))) {
                etName.error = "Only alphabets allowed"
                etName.requestFocus()
                return@setOnClickListener
            }

            // ✅ Phone validation
            if (!phone.matches(Regex("^[0-9]{10}$"))) {
                etPhone.error = "Enter valid 10-digit number"
                etPhone.requestFocus()
                return@setOnClickListener
            }

            // ✅ Email validation
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter valid email"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            // ✅ Gender
            if (!radioMale.isChecked && !radioFemale.isChecked) {
                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Language
            if (spinner.selectedItemPosition == 0) {
                Toast.makeText(this, "Select language", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 🎉 SUCCESS
            Toast.makeText(this, "Profile Completed 🎉", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)

            // 🔥 PAGE TRANSITION ANIMATION
            overridePendingTransition(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )

            // ❌ DO NOT USE finish()
        }
    }
}