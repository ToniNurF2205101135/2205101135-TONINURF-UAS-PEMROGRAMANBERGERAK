package com.example.sewaps

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.sewaps.database.AppDatabase
import com.example.sewaps.database.RiwayatSewa
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class SewaPsActivity : AppCompatActivity() {

    // simpan total harga
    private var totalHarga = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sewa_ps)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val spinnerPs = findViewById<Spinner>(R.id.spinnerPs)
        val edtHari = findViewById<EditText>(R.id.edtHari)
        val tvHarga = findViewById<TextView>(R.id.tvHarga)
        val edtBayar = findViewById<EditText>(R.id.edtBayar)
        val btnHitung = findViewById<Button>(R.id.btnHitung)
        val btnProses = findViewById<Button>(R.id.btnProses)

        // Spinner PS
        val psList = arrayOf("PS 3", "PS 4", "PS 5")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, psList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPs.adapter = adapter

        // HITUNG HARGA
        btnHitung.setOnClickListener {

            val hariText = edtHari.text.toString()
            if (hariText.isEmpty()) {
                Toast.makeText(this, "Masukkan lama sewa", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hari = hariText.toInt()

            val hargaPerHari = when (spinnerPs.selectedItem.toString()) {
                "PS 3" -> 50_000
                "PS 4" -> 100_000
                else -> 150_000
            }

            totalHarga = hargaPerHari * hari
            tvHarga.text = formatRupiah(totalHarga)
        }

        // PROSES SEWA
        btnProses.setOnClickListener {

            val nama = edtNama.text.toString()
            val ps = spinnerPs.selectedItem.toString()
            val hariText = edtHari.text.toString()
            val bayarText = edtBayar.text.toString()

            if (nama.isEmpty() || hariText.isEmpty() || bayarText.isEmpty()) {
                Toast.makeText(this, "Lengkapi data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val hari = hariText.toInt()
            val bayar = bayarText.toInt()

            if (bayar < totalHarga) {
                Toast.makeText(this, "Uang bayar kurang!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val kembali = bayar - totalHarga
            val tanggal = System.currentTimeMillis() // Long ✅

            // SIMPAN KE DATABASE
            val riwayat = RiwayatSewa(
                nama = nama,
                ps = ps,
                lamaHari = hari,
                total = totalHarga,
                bayar = bayar,
                kembali = kembali,
                tanggal = tanggal
            )

            val db = AppDatabase.getDatabase(this)

            lifecycleScope.launch {
                db.riwayatSewaDao().insert(riwayat)

                val intent = Intent(this@SewaPsActivity, StrukActivity::class.java)
                intent.putExtra("nama", nama)
                intent.putExtra("ps", ps)
                intent.putExtra("hari", hari)
                intent.putExtra("total", totalHarga)
                intent.putExtra("bayar", bayar)
                startActivity(intent)
            }
        }
    }

    private fun formatRupiah(angka: Int): String {
        val localeID = Locale("id", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(angka).replace(",00", "")
    }
}
