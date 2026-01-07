package com.example.sewaps.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "riwayat_sewa")
data class RiwayatSewa(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nama: String,
    val ps: String,
    val lamaHari: Int,
    val total: Int,
    val bayar: Int,
    val kembali: Int,
    val tanggal: Long
)
