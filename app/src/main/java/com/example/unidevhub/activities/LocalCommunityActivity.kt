package com.example.unidevhub.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidevhub.R
import com.example.unidevhub.adapters.GlobalCommunityAdapter
import com.example.unidevhub.databinding.ActivityLocalCommunityBinding
import com.example.unidevhub.models.GlobalCommunity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale

class LocalCommunityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLocalCommunityBinding
    private val LOCATION_PERMISSION_REQUEST_CODE = 100
    private var userLocation: String? = null
    private var communityRecycler: RecyclerView? = null
    private var recyclerviewAdapter: GlobalCommunityAdapter? = null
    private var searchView: EditText? = null
    private var search: CharSequence = ""
    private val locationPermissionCode = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLocalCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding.textViewSelectLocation.setOnClickListener {
            checkLocationPermission()
        }

    }


    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission has already been granted
            getUserLocation()
        }
    }


    private fun getUserLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            val locationListener: LocationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    val geocoder = Geocoder(this@LocalCommunityActivity, Locale.getDefault())
                    try {
                        val addresses: List<Address>? = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        if (addresses != null && addresses.isNotEmpty()) {
                            val address: String = addresses[0].getAddressLine(0)
                            userLocation = address
                            binding.textViewSelectLocation.text = userLocation
                            showCommunities()
                        // Update the textView with location name
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    locationManager.removeUpdates(this)
                }

                @Deprecated("Deprecated in Java")
                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                }

                override fun onProviderEnabled(provider: String) {}
                override fun onProviderDisabled(provider: String) {}
            }

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Request permissions
                return
            }
            locationManager.requestSingleUpdate(
                LocationManager.NETWORK_PROVIDER,
                locationListener,
                null
            )
        } else {
            // Show a dialog to enable GPS
            Toast.makeText(this, "Please enable GPS", Toast.LENGTH_SHORT).show()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }
    }

    private fun showCommunities() {
        val userDataList: MutableList<GlobalCommunity> = ArrayList<GlobalCommunity>()
        userDataList.add(
            GlobalCommunity(
                "CodeChefChapter",
                "Active",
                R.drawable.localcommunity1
            )
        )
        userDataList.add(
            GlobalCommunity(
                "GDSC PCCOE Chapter",
                "Active",
                R.drawable.localcommunity2
            )
        )
        userDataList.add(
            GlobalCommunity(
                "GeeksForGeeks PCCOE Chapter",
                "Active",
                R.drawable.localcommunity3
            )
        )
        userDataList.add(
            GlobalCommunity(
                "ReactDecelopers of PCCOE",
                "Active",
                R.drawable.localcommunity4
            )
        )

        setUserRecycler(userDataList)


        searchView = binding.editTextSearchLocation

        searchView!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                recyclerviewAdapter!!.filter.filter(charSequence)
                search = charSequence
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
    private fun setUserRecycler(userDataList: List<GlobalCommunity>) {
        communityRecycler = binding.recyclerViewCommunities
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        communityRecycler!!.setLayoutManager(layoutManager)
        recyclerviewAdapter = GlobalCommunityAdapter(this, userDataList)
        communityRecycler!!.setAdapter(recyclerviewAdapter)
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with getting the location
                getUserLocation()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }


    }

}