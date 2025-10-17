package roni.putra.kotlin.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import roni.putra.kotlin.R
import roni.putra.kotlin.intent.HasilActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormMahasiswaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_mahasiswa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //tambahkan variable
        val etNim = findViewById<EditText>(R.id.etNim)
        val etNama = findViewById<EditText>(R.id.etNama)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etTlpn = findViewById<EditText>(R.id.etTlpn)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val spJurusan = findViewById<Spinner>(R.id.spJurusan)
        val spProdi = findViewById<Spinner>(R.id.spProdi)
        val rgJekel = findViewById<RadioGroup>(R.id.rgJekel)
        val chkBaca = findViewById<CheckBox>(R.id.chkBaca)
        val chkTraveling = findViewById<CheckBox>(R.id.chkTraveling)
        val chkCoding = findViewById<CheckBox>(R.id.chkCoding)
        val tvTglLahir = findViewById<TextView>(R.id.tvTglLahir)
        val etJam = findViewById<TextView>(R.id.etJam)


        val listJurusan =
            arrayOf("Teknologi Informasi", "Teknik Sipil", "Admnistrasi Niaga", "Akuntansi")

        val listProdi =
            arrayOf("D3-Manajemen Informatika", "D3-Teknik Komputer", "D4-TRPL", "D4-Alih Kredit")


        val adapterJurusan: ArrayAdapter<*>
        val adapterProdi: ArrayAdapter<*>

        adapterJurusan = ArrayAdapter(this, R.layout.spin_style, listJurusan)
        adapterProdi = ArrayAdapter(this, R.layout.spin_style, listProdi)

        //panggil widget spinner
        spJurusan.adapter = adapterJurusan
        spProdi.adapter = adapterProdi

        val myCalendar = Calendar.getInstance()
        var datePicker: DatePickerDialog.OnDateSetListener
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val formatIndo = "dd-MMMM-yyyy"
            val stringFormat = SimpleDateFormat(formatIndo, Locale.US)
            //isi tanggal ke textview
            tvTglLahir.text = stringFormat.format(myCalendar.time)
        }
        //Tampilkan calendar
        tvTglLahir.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }

        val currentTime = Calendar.getInstance()
        val timePicker: TimePickerDialog.OnTimeSetListener
        timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentTime.set(Calendar.MINUTE, minute)
            val formatTime = SimpleDateFormat("HH:mm").format(currentTime.time)
            etJam.text = formatTime
        }

        //tampilkan timepicker
        etJam.setOnClickListener {
            TimePickerDialog(
                this,
                timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }


        //Event ketika button di klik
        btnSimpan.setOnClickListener {
            val pilJekel = findViewById<RadioButton>(rgJekel.checkedRadioButtonId)
            var pilHobby = ""

            if (chkBaca.isChecked) {
                pilHobby += "+${chkBaca.text}\n"
            }
            if (chkTraveling.isChecked) {
                pilHobby += "+${chkTraveling.text}\n"
            }
            if (chkCoding.isChecked) {
                pilHobby += "+${chkCoding.text}"
            }
            //Pilih
            //Baca
            //Traveling

            //Tampilkan ke tvHasil
            val bundle = Bundle()
            bundle.putString("pNim",etNim.text.toString())
            bundle.putString("pNama",etNim.text.toString())
            bundle.putString("pHobby",pilHobby)
            val intent = Intent(this, HasilActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)


            tvHasil.text = "Nim : ${etNim.text}\n" +
                    "Nama : ${etNama.text}\n" +
                    "Email : ${etEmail.text}\n" +
                    "No Tlpn : ${etTlpn.text}\n" +
                    "Jurusan : ${spJurusan.selectedItem}\n" +
                    "Prodi : ${spProdi.selectedItem}\n" +
                    "Jenis Kelamin : ${pilJekel.text}\n" +
                    "Hobby = \n$pilHobby" +
                    "Tanggal Lahir = ${tvTglLahir.text}"
        }
    }
}