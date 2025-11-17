package roni.putra.kotlin.list.recycler.linear

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlin.R

class GridRecyeclerActivity : AppCompatActivity() {
    private lateinit var recGrid: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grid_recyecler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recGrid = findViewById(R.id.recGrid)
    }

    override fun onStart() {
        super.onStart()
        setProduk()
    }

    private fun setProduk() {
        val produk = listOf<ProdukModel>(
            ProdukModel(R.drawable.laptop_1,"Laptop 1","Rp. 4.500.000",4.5f,"Padang"),
            ProdukModel(R.drawable.laptop_2,"Laptop 2","Rp. 4.700.000",3.5f,"Jakarta"),
            ProdukModel(R.drawable.laptop_3,"Laptop 3","Rp. 5.700.000",4.0f,"Bandung"),
            ProdukModel(R.drawable.laptop_4,"Laptop 4","Rp. 7.700.000",3.0f,"Tanggerang"),
        )

        val produkAdapter = ProdukAdapter(produk)
        recGrid.adapter = produkAdapter
    }
}