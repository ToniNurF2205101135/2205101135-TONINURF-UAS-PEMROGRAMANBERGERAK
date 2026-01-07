package com.example.sewaps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val layoutWa = findViewById<LinearLayout>(R.id.layoutWa)
        val layoutIg = findViewById<LinearLayout>(R.id.layoutIg)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // WhatsApp
        layoutWa.setOnClickListener {
            val nomorWa = "6285850345846" // tanpa 0 depan
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/$nomorWa")
            )
            startActivity(intent)
        }

        // Instagram
        layoutIg.setOnClickListener {
            val usernameIg = "Latihfnr_"
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://instagram.com/$usernameIg")
            )
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
