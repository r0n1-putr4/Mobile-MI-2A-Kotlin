package roni.putra.kotlin.menu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import roni.putra.kotlin.R
import roni.putra.kotlin.intent.LatActivity

class MenuActivity : AppCompatActivity() {
    private lateinit var toolBar: Toolbar
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)

        toolBar = findViewById(R.id.toolBar)
        bottomNav = findViewById(R.id.bottomNav)
        setSupportActionBar(toolBar)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.bottom_order -> {
                    startActivity(Intent(this@MenuActivity, LatActivity::class.java))
                    true
                }

                else -> false
            }
        }

    }

    //alt + ins atau ctrl + o
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.top_logout -> {
                Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.top_group -> {
                startActivity(Intent(this@MenuActivity, LatActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}