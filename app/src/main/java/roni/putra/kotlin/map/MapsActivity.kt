package roni.putra.kotlin.map

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import roni.putra.kotlin.R
import roni.putra.kotlin.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fabStyleMap: FloatingActionButton
    private lateinit var fabStyleMapStandard: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fabStyleMap = findViewById(R.id.fabStyleMap)
        fabStyleMapStandard = findViewById(R.id.fabStyleMapStandard)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val zoomMap = 15f

        // Add a marker in Sydney and move the camera
        val pnp = LatLng(-0.9158477091052921, 100.35641334493916)
        mMap.addMarker(MarkerOptions()
            .position(pnp)
            .title("Politeknik Negeri Padang")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_hotel))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pnp, zoomMap))

        //tambahkan data Map
        val listMap = listOf(
            MapModel(-0.9154177618750032, 100.35443394640298,
                "Baba Guest House Syari'ah",R.drawable.icon_hotel),
            MapModel(-0.9140874456750067, 100.35569306139264,
                "KELAPA GADING GUESTHOUSE",R.drawable.icon_rs),
            MapModel(-0.915160188749055, 100.35951293354577,
                "Whiz Prime Hotel Khatib Sulaiman Padang",R.drawable.icon_hotel),
            MapModel(-0.9146452731158794, 100.3573669194483,
                "Fafan Budget Hotel",R.drawable.icon_rs),
        )

        listMap.forEach {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(it.Lat,it.Lng))
                .title(it.Title)
                .icon(BitmapDescriptorFactory.fromResource(it.gambar))
            )
        }
        //panggil method setStyleMapAubergine
        setStyleMapAubergine()
    }

    private fun setStyleMapAubergine(){
        try {
            fabStyleMap.setOnClickListener {
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,
                    R.raw.map_style_aubergine))
            }
        }catch (e: Resources.NotFoundException){
            //Toast.makeText(this, Toast.)
            Log.e("Error Style",e.toString())
        }
    }
    private fun setStyleMapStandard(){
        try {
            fabStyleMap.setOnClickListener {
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this,
                    R.raw.map_style_aubergine))
            }
        }catch (e: Resources.NotFoundException){
            //Toast.makeText(this, Toast.)
            Log.e("Error Style",e.toString())
        }
    }
}