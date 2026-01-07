package com.example.sewaps.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RiwayatSewaDao {

    @Insert
    suspend fun insert(riwayat: RiwayatSewa)

    @Query("SELECT * FROM riwayat_sewa ORDER BY id DESC")
    suspend fun getAll(): List<RiwayatSewa>
}
