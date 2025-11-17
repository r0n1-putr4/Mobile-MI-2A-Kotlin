package roni.putra.kotlin.intent

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.kotlin.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class LatActivity : AppCompatActivity() {
    private lateinit var etNoMeja: EditText
    private lateinit var etNama: EditText
    private lateinit var tvTglOrder: TextView
    private lateinit var tvJamOrder: TextView
    private lateinit var chkPokat: CheckBox
    private lateinit var chkMangga: CheckBox
    private lateinit var chkJeruk: CheckBox
    private lateinit var chkTeh: CheckBox
    private lateinit var etJlhPokat: EditText
    private lateinit var etJlhMangga: EditText
    private lateinit var etJlhJeruk: EditText
    private lateinit var etJlhTeh: EditText
    private lateinit var spPelanggan: Spinner
    private lateinit var btnSimpan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNoMeja = findViewById(R.id.etNoMeja)
        etNama = findViewById(R.id.etNama)
        tvTglOrder = findViewById(R.id.tvTglOrder)
        tvJamOrder = findViewById(R.id.tvJamOrder)
        chkPokat = findViewById(R.id.chkPokat)
        chkMangga = findViewById(R.id.chkMangga)
        chkJeruk = findViewById(R.id.chkJeruk)
        chkTeh = findViewById(R.id.chkTeh)
        etJlhPokat = findViewById(R.id.etJlhPokat)
        etJlhMangga = findViewById(R.id.etJlhMangga)
        etJlhJeruk = findViewById(R.id.etJlhJeruk)
        etJlhTeh = findViewById(R.id.etJlhTeh)
        spPelanggan = findViewById(R.id.spPelanggan)
        btnSimpan = findViewById(R.id.btnSimpan)

    }

    override fun onStart() {
        super.onStart()
        setTanggalOrder()
        setJamOrder()
        setJenisPelanggan()
        prosesBayar()

    }

    fun prosesBayar(){
        btnSimpan.setOnClickListener {
            var harga_jumlah = 0.0
            var total_bayar: Double

            if (chkPokat.isChecked){
                harga_jumlah += etJlhPokat.text.toString().toDouble() * 10_000
            }
            if(chkMangga.isChecked) {
                harga_jumlah += etJlhMangga.text.toString().toDouble() * 15_000
            }
            if(chkJeruk.isChecked) {
                harga_jumlah += etJlhJeruk.text.toString().toDouble() * 8_000
            }
            if(chkTeh.isChecked) {
                harga_jumlah += etJlhTeh.text.toString().toDouble() * 5_000
            }


            val disc = if(spPelanggan.selectedItem.equals("Baru")){
                0.1
            }else
                0.2

            val potongan = if(harga_jumlah>100_000){
                10_000
            }else
                0
            total_bayar = harga_jumlah - disc * harga_jumlah - potongan

            Log.d("harga_jumlah","$harga_jumlah")
            Log.d("disc","${disc * harga_jumlah}")
            Log.d("potongan","$potongan")
            Log.d("total_bayar","$total_bayar")
        }
    }

    fun setJenisPelanggan(){
        val listPelanggan = arrayOf("Baru", "Member")
        val adapterPelanggan = ArrayAdapter(this, R.layout.spin_style, listPelanggan)
        spPelanggan.adapter = adapterPelanggan
    }

    fun setTanggalOrder(){
        val myCalendar = Calendar.getInstance()
        val datePicker: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = "dd-MMMM-yyyy"
            val stringFormat = SimpleDateFormat(formatIndo, Locale.US)
            //isi tanggal ke textview
            tvTglOrder.text = stringFormat.format(myCalendar.time)
        }
        tvTglOrder.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    fun setJamOrder(){
        val currentTime = Calendar.getInstance()
        val timePicker: TimePickerDialog.OnTimeSetListener =
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentTime.set(Calendar.MINUTE, minute)
            val formatTime = SimpleDateFormat("HH:mm").format(currentTime.time)
                tvJamOrder.text = formatTime
        }

        tvJamOrder.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}