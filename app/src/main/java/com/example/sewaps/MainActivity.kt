package com.example.sewaps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.AlertDialog


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // INFORMASI PS
        findViewById<Button>(R.id.btnInfo).setOnClickListener {
            startActivity(Intent(this, InfoPsActivity::class.java))
        }

        // SEWA PS
        findViewById<Button>(R.id.btnSewa).setOnClickListener {
            startActivity(Intent(this, SewaPsActivity::class.java))
        }

        // CONTACT
        findViewById<Button>(R.id.btnContact).setOnClickListener {
            startActivity(Intent(this, ContactActivity::class.java))
        }

        // RIWAYAT SEWA ➜ HALAMAN BARU
        findViewById<Button>(R.id.btnRiwayat).setOnClickListener {
            startActivity(Intent(this, RiwayatSewaActivity::class.java))
        }
        // KELUAR APLIKASI
        findViewById<Button>(R.id.btnKeluar).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Keluar Aplikasi")
                .setMessage("Yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya") { _, _ ->
                    finishAffinity() // keluar dari aplikasi
                }
                .setNegativeButton("Batal", null)
                .show()
        }

    }
}
