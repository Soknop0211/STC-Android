package com.eazy.stcbusiness.ui.transportation.bookschedule

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationDestinataionBinding
import com.eazy.stcbusiness.databinding.ActivityTransportationWaitingBinding
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.utils.maps.GPSTracker
import com.eazy.stcbusiness.view_model.TransportationWaitingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TransportationWaitingActivity : BaseActivity<ActivityTransportationWaitingBinding, TransportationWaitingViewModel>(),
    OnClickCallBackListener, OnMapReadyCallback {

    companion object {
        fun gotoTransportationWaitingActivity(activity: Context){
            val intent = Intent(activity, TransportationWaitingActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override val layoutId = R.layout.activity_transportation_waiting

    override val mViewModel: TransportationWaitingViewModel by viewModels()
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
            .findFragmentById(R.id.google_map) as SupportMapFragment?)!!

        mapFragment.getMapAsync(this)

        callLocation()

    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)
    }


    override fun onClickCallBack() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        zoomMap(latitude, longitude)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.uiSettings.isCompassEnabled = false //hide compass
        // mMap.isMyLocationEnabled = true
        // mMap.uiSettings.isMyLocationButtonEnabled = true

    }

    private fun zoomMap(lat: Double, lng: Double) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
    }

    private fun callLocation() {
        val gps = GPSTracker(this)
        if (gps.canGetLocation()) {
            if (latitude == 0.0 && longitude == 0.0) {
                latitude = gps.getLat()
                longitude = gps.getLat()
            }
        }
    }
}