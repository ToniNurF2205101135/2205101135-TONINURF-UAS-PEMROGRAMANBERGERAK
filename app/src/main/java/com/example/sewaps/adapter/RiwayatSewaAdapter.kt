package com.example.sewaps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sewaps.R
import com.example.sewaps.database.RiwayatSewa
import java.text.NumberFormat
import java.util.Locale

class RiwayatSewaAdapter(
    private var list: List<RiwayatSewa>
) : RecyclerView.Adapter<RiwayatSewaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvPs: TextView = itemView.findViewById(R.id.tvPs)
        val tvHari: TextView = itemView.findViewById(R.id.tvHari)
        val tvTotal: TextView = itemView.findViewById(R.id.tvTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_riwayat_sewa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.tvNama.text = "Nama: ${data.nama}"
        holder.tvPs.text = "PS: ${data.ps}"
        holder.tvHari.text = "Lama: ${data.lamaHari} hari"
        holder.tvTotal.text = "Total: ${formatRupiah(data.total)}"
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<RiwayatSewa>) {
        list = newList
        notifyDataSetChanged()
    }

    private fun formatRupiah(angka: Int): String {
        val localeID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localeID)
        return format.format(angka).replace(",00", "")
    }
}
