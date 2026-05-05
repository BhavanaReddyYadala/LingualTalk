package com.example.lingualtalk

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etLanguage = findViewById<AutoCompleteTextView>(R.id.etLanguage)

        val rbMale = findViewById<RadioButton>(R.id.rbMale)
        val rbFemale = findViewById<RadioButton>(R.id.rbFemale)

        val btnContinue = findViewById<Button>(R.id.btnContinue)

        //Language dropdown
        val languages = arrayOf(
            "తెలుగు", "हिन्दी", "தமிழ்", "ಕನ್ನಡ", "മലയാളം",
            "मराठी", "বাংলা", "ગુજરાતી", "ਪੰਜਾਬੀ", "اردو",
            "English", "Français", "Español", "Deutsch",
            "Português", "العربية", "日本語", "한국어",
            "中文", "Italiano"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            languages
        )

        etLanguage.setAdapter(adapter)

    //Default text
        etLanguage.setText("Select Language", false)

    //Smooth dropdown
        etLanguage.setOnClickListener {
            etLanguage.showDropDown()
        }

        btnContinue.setOnClickListener {

            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val language = etLanguage.text.toString().trim()

            val gender = when {
                rbMale.isChecked -> "Male"
                rbFemale.isChecked -> "Female"
                else -> ""
            }

            if (name.isEmpty() || !name.matches(Regex("^[A-Za-z ]+$"))) {
                etName.error = "Enter valid name"
                return@setOnClickListener
            }

            if (!phone.matches(Regex("^[0-9]{10}$"))) {
                etPhone.error = "Enter valid number"
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter valid email"
                return@setOnClickListener
            }

            if (gender.isEmpty()) {
                Toast.makeText(this, "Select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (language.isEmpty()) {
                etLanguage.error = "Select language"
                return@setOnClickListener
            }

            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }
    }
}