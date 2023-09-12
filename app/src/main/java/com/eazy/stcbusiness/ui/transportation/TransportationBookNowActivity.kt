package com.eazy.stcbusiness.ui.transportation

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationBookNowBinding
import com.eazy.stcbusiness.utils.listener.OnClickCallBackListener
import com.eazy.stcbusiness.utils.maps.GPSTracker
import com.eazy.stcbusiness.view_model.TransportationBookingNowViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class TransportationBookNowActivity : BaseActivity<ActivityTransportationBookNowBinding, TransportationBookingNowViewModel>(),
    OnClickCallBackListener, OnMapReadyCallback {

    override val layoutId = R.layout.activity_transportation_book_now
    override val mViewModel: TransportationBookingNowViewModel by viewModels()

    companion object {
        fun gotoTransportationBookNowActivity(activity: Context){
            val intent = Intent(activity, TransportationBookNowActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var mMap: GoogleMap
    private lateinit var geocoder: Geocoder
    private var saveLat = 0.0
    private  var saveLng = 0.0
    private  var fullAddress  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = (supportFragmentManager
            .findFragmentById(R.id.google_map) as SupportMapFragment?)!!

        mapFragment.getMapAsync(this)

        callLocation()

        geocoder = Geocoder(this, Locale.getDefault())
    }

    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)
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

    override fun onClickCallBack() {
        TransportationDestinationActivity.gotoTransportationDestinationActivity(this)
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
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.uiSettings.isCompassEnabled = false //hide compass

        mMap.setOnCameraIdleListener {
            displayAddressByImageCenter()
        }
    }

    private fun zoomMap(lat: Double, lng: Double) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
    }

    private lateinit var centerImage : ImageView
    private fun displayAddressByImageCenter(): String {
        centerImage = mBinding.centerImage

        val mMainLayout = mBinding.mainLayout
        try {
//            val h = centerImage.height
//            val w = centerImage.width / 2
//            val x = centerImage.x.toInt() + w
//            val y = centerImage.y.toInt() + h / 2

            val w =mMainLayout.width
            val h = mMainLayout.height
            val hMarker: Int = centerImage.height

            val xCenter = w / 2
            val yCenter = h / 2
            val markerCenter = hMarker / 2

            val ll = mMap.projection.fromScreenLocation(Point(xCenter, yCenter))

            val addresses = geocoder.getFromLocation(ll.latitude, ll.longitude, 1)
            saveLat = ll.latitude
            saveLng = ll.longitude

            if (addresses != null) {
                val fullAdd: String = getFullAddress(addresses)
                mViewModel.mTitle.set(String.format("%s", fullAdd))
                fullAddress = fullAdd
                return fullAdd
            }


        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ""
    }
    private fun getFullAddress(addresses: List<Address>): String {
        return if (addresses.isNotEmpty()) {
            addresses[0].getAddressLine(0)
        } else ""
    }
}