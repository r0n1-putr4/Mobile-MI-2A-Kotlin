package roni.putra.kotlin.list.recycler.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlin.R

class BeritaAdapter(
    private val listBerita : List<BeritaModel>
) : RecyclerView.Adapter<BeritaAdapter.ViewBerita>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBerita {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_linear, parent, false)

        return ViewBerita(view)
    }

    override fun onBindViewHolder(holder: ViewBerita, position: Int) {
        val berita = listBerita[position]
        holder.imgBerita.setImageResource(berita.gambar)
        holder.tvJudul.text = berita.judul
        holder.tvTanggal.text = berita.tanggal

    }

    override fun getItemCount() = listBerita.size

    class ViewBerita(view: View) : RecyclerView.ViewHolder(view) {
        val imgBerita = view.findViewById<ImageView>(R.id.imgBerita)
        val tvJudul = view.findViewById<TextView>(R.id.tvJudul)
        val tvTanggal = view.findViewById<TextView>(R.id.tvTanggal)
    }
}