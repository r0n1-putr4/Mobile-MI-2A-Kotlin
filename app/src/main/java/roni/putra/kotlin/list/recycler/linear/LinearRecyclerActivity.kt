package roni.putra.kotlin.list.recycler.linear

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlin.R

class LinearRecyclerActivity : AppCompatActivity() {
    private lateinit var rvBerita: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_linear_recycler)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvBerita = findViewById(R.id.rvBerita)
    }

    override fun onStart() {
        super.onStart()
        setBerita()
    }

    fun setBerita() {
        val berita = listOf<BeritaModel>(
            BeritaModel(
                R.drawable.gambar_1,
                "Judul Berita satu",
                "10 Oktober 2025",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et "
            ),
            BeritaModel(
                R.drawable.gambar_2,
                "Judul Berita dua",
                "9 Oktober 2025",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata"
            ),
            BeritaModel(
                R.drawable.gambar_3,
                "Judul Berita tiga",
                "8 Oktober 2025",
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata"
            )
        )

        val beritaAdapter = BeritaAdapter(berita, object : BeritaAdapter.OnAdapterListener {
            override fun onClick(result: BeritaModel) {
                val bundle = Bundle()
                bundle.putInt("gambar", result.gambar)
                bundle.putString("judul", result.judul)
                bundle.putString("tanggal", result.tanggal)
                bundle.putString("isi", result.isi_berita)
                val intent = Intent(this@LinearRecyclerActivity,
                    DetailBeritaActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)

            }
        })
        rvBerita.adapter = beritaAdapter
    }
}