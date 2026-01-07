package com.example.sewaps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sewaps.adapter.RiwayatSewaAdapter
import com.example.sewaps.database.AppDatabase
import kotlinx.coroutines.launch

class RiwayatSewaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat_sewa)

        val rvRiwayat = findViewById<RecyclerView>(R.id.rvRiwayat)
        rvRiwayat.layoutManager = LinearLayoutManager(this)

        val adapter = RiwayatSewaAdapter(emptyList())
        rvRiwayat.adapter = adapter

        val db = AppDatabase.getDatabase(this)

        lifecycleScope.launch {
            val data = db.riwayatSewaDao().getAll()
            adapter.setData(data)
        }
    }
}
