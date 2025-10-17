package roni.putra.kotlin.list.array_adapter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.kotlin.R

class ListArrayAdapterActivity : AppCompatActivity() {
    private lateinit var lvPemograman: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_array_adapter)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lvPemograman = findViewById(R.id.lvPemograman)
    }

    override fun onStart() {
        super.onStart()
        val listPemograman = arrayOf("Kotlin", "Java", "PHP", "Dart", "C", "Python")
        val arrayAdapterPemograman = ArrayAdapter(this, R.layout.list_style,
            listPemograman)
        lvPemograman.adapter = arrayAdapterPemograman
    }
}