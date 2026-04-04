package com.example.primetalkerui

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
            "Select Language", // default empty
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
                return position != 0 // ❌ Disable first item
            }
        }

        spinner.adapter = adapter



        // 🔄 Function to update progress
        fun updateProgress() {
            var progress = 0

            if (etName.text.toString().trim().isNotEmpty()) progress += 20
            if (etPhone.text.toString().trim().isNotEmpty()) progress += 20
            if (etEmail.text.toString().trim().isNotEmpty()) progress += 20
            if (radioMale.isChecked || radioFemale.isChecked) progress += 20
            if (spinner.selectedItemPosition != 0) progress += 20

            progressBar.progress = progress
            tvPercent.text = "$progress%"
        }

        // 🔤 TextWatcher (for live updates)
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

        // 🚀 Button validation
        btnContinue.setOnClickListener {

            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Enter name"
                return@setOnClickListener
            }

            if (!name.matches(Regex("^[a-zA-Z ]+$"))) {
                etName.error = "Only letters allowed"
                return@setOnClickListener
            }

            if (phone.length != 10 || !phone.all { it.isDigit() }) {
                etPhone.error = "Enter valid number"
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter valid email"
                return@setOnClickListener
            }

            if (!radioMale.isChecked && !radioFemale.isChecked) {
                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (spinner.selectedItemPosition == 0) {
                Toast.makeText(this, "Select language", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Profile Completed 🎉", Toast.LENGTH_SHORT).show()
        }
    }
}