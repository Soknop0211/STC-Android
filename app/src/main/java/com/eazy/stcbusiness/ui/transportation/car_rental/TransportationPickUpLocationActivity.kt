package com.eazy.stcbusiness.ui.transportation.car_rental

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BetterActivityResult
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationPickUpLocationBinding
import com.eazy.stcbusiness.model.ItemRecentSearchModel
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationDestinationActivity
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.maps.GPSTracker
import com.eazy.stcbusiness.view_model.OnListenerTransportationPickUpLocation
import com.eazy.stcbusiness.view_model.TransportationPickUpLocationViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TransportationPickUpLocationActivity : BaseActivity<ActivityTransportationPickUpLocationBinding, TransportationPickUpLocationViewModel>(),
    OnListenerTransportationPickUpLocation, OnMapReadyCallback {

    override val layoutId: Int = R.layout.activity_transportation_pick_up_location
    override val mViewModel: TransportationPickUpLocationViewModel by viewModels()

    companion object {
        fun gotoTransportationPickUpLocationActivity(
            activity: Activity,
            activityResult: BetterActivityResult.OnActivityResult<ActivityResult>,
        ) {
            val intent = Intent(activity, TransportationPickUpLocationActivity::class.java)
            if (activity is SampleBaseActivity) {
                activityResult.let {
                    activity.activityLauncher.launch(intent, activityResult)
                }
            }
        }
    }

    private lateinit var mMap: GoogleMap
    private lateinit var geocoder: Geocoder
    private var mGetLatLng : LatLng?= null
    private var mAddress : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

        initGoogleMap(savedInstanceState)

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

    override fun onClickCurrentLocation() {
        zoomMap(latitude, longitude)
    }


    override fun onClickSearchButton() {
        TransportationSearchLocationDialog.gotoTransportationSearchLocationDialog(supportFragmentManager, object : TransportationSearchLocationDialog.OnClickCallBackListener {
            override fun onCallBackItemListener(mItemRecentSearchModel: ItemRecentSearchModel) {

            }

        })
    }

    override fun onClickCallBack() {
        val mIntent = Intent()
        mIntent.putExtra(TransportationDestinationActivity.LATITUDE,  mGetLatLng?.latitude ?: 0.0)
        mIntent.putExtra(TransportationDestinationActivity.LONGITUDE,  mGetLatLng?.longitude ?: 0.0)
        mIntent.putExtra(TransportationDestinationActivity.ADDRESS,  mAddress ?: "")
        setResult(RESULT_OK, mIntent)
        finish()
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

        mMap.setOnCameraIdleListener {
            val w = mBinding.containerMap.width
            val h = mBinding.containerMap.height
            val hMarker: Int = mBinding.centerImage.height

            val xCenter = w / 2
            val yCenter = h / 2
            val markerCenter = hMarker / 2

            val mLatLng = mMap.projection.fromScreenLocation(Point(xCenter, yCenter + markerCenter))

            mGetLatLng = LatLng(mLatLng.latitude, mLatLng.longitude)

            mAddress = Utils.getStringAddress(
                this,
                mLatLng.latitude, mLatLng.longitude
            )

        }
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        mBinding.mapview.onCreate(savedInstanceState)
        mBinding.mapview.onResume()
        mBinding.mapview.getMapAsync(this)
    }

    private fun zoomMap(lat: Double, lng: Double) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
    }


    override fun onResume() {
        mBinding.mapview.onResume()
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        mBinding.mapview.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.mapview.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mBinding.mapview.onLowMemory()
    }

}