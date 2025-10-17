package roni.putra.kotlin.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.kotlin.R

class ExplicitIntentActivity : AppCompatActivity() {
    private lateinit var btnProses: Button
    private lateinit var etNidn: EditText
    private lateinit var etNama: EditText
    private lateinit var etUmur: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_explicit_intent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //inisialisasi
        btnProses = findViewById(R.id.btnProses)
        etNidn = findViewById(R.id.etNidn)
        etNama = findViewById(R.id.etNama)
        etUmur = findViewById(R.id.etUmur)
    }

    override fun onStart() {
        super.onStart()
        proses();
    }

    fun proses(){
        btnProses.setOnClickListener {
            //Pindah activity
            //cara pertama
            //startActivity(Intent(this, HasilActivity::class.java))

            //cara kedua
            val bundle = Bundle()
            bundle.putString("pNidn",etNidn.text.toString())
            bundle.putString("pNama",etNama.text.toString())
            bundle.putInt("pUmur",etUmur.text.toString().toInt())
            val intent = Intent(this, HasilActivity::class.java)
            //pindah ke activity
            intent.putExtras(bundle)
            startActivity(intent)

        }
    }
}