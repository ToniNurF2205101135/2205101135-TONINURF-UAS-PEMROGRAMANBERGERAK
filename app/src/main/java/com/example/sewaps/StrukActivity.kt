package com.example.sewaps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class StrukActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_struk)

        val tvNama = findViewById<TextView>(R.id.tvNama)
        val tvPs = findViewById<TextView>(R.id.tvPs)
        val tvHari = findViewById<TextView>(R.id.tvHari)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvBayar = findViewById<TextView>(R.id.tvBayar)
        val tvKembali = findViewById<TextView>(R.id.tvKembali)
        val btnHome = findViewById<Button>(R.id.btnHome)

        // ambil data dari intent
        val nama = intent.getStringExtra("nama")
        val ps = intent.getStringExtra("ps")
        val hari = intent.getIntExtra("hari", 0)
        val total = intent.getIntExtra("total", 0)
        val bayar = intent.getIntExtra("bayar", 0)

        val kembali = bayar - total

        // tampilkan data (PAKAI FORMAT RUPIAH)
        tvNama.text = "Nama : $nama"
        tvPs.text = "PS : $ps"
        tvHari.text = "Lama Sewa : $hari hari"
        tvTotal.text = "Total : ${formatRupiah(total)}"
        tvBayar.text = "Uang Bayar : ${formatRupiah(bayar)}"
        tvKembali.text = "Uang Kembali : ${formatRupiah(kembali)}"

        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // FUNGSI FORMAT RUPIAH (POSISI SUDAH BENAR)
    private fun formatRupiah(angka: Int): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(angka).replace(",00", "")
    }
}
