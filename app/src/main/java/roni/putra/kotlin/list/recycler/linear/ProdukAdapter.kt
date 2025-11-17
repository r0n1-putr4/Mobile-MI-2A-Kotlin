package roni.putra.kotlin.list.recycler.linear

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlin.R

class ProdukAdapter(
    private val listItemProduk: List<ProdukModel>
) : RecyclerView.Adapter<ProdukAdapter.ViewProduk>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewProduk {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ViewProduk(view)
    }

    override fun onBindViewHolder(holder: ViewProduk, position: Int) {
        val dataProduk = listItemProduk[position]
        holder.imgGambarProduk.setImageResource(dataProduk.gambar_produk)
        holder.tvNamaProduk.text = dataProduk.nama_produk
        holder.tvHarga.text = dataProduk.harga
        holder.tvRating.text = "${dataProduk.rating}"
        holder.ratingBar.rating = dataProduk.rating
        holder.tvLokasi.text = dataProduk.lokasi
    }

    override fun getItemCount() = listItemProduk.size

    class ViewProduk(view: View) : RecyclerView.ViewHolder(view) {
        val imgGambarProduk = view.findViewById<ImageView>(R.id.imgGambarProduk)
        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaProduk)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvRating = view.findViewById<TextView>(R.id.tvRating)
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val tvLokasi = view.findViewById<TextView>(R.id.tvLokasi)
    }
}