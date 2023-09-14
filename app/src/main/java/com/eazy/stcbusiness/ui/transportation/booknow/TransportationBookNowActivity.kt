package com.eazy.stcbusiness.ui.transportation.booknow

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.location.Geocoder
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.eazy.stcbusiness.BR
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.BaseActivity
import com.eazy.stcbusiness.base.BetterActivityResult
import com.eazy.stcbusiness.base.SampleBaseActivity
import com.eazy.stcbusiness.databinding.ActivityTransportationBookNowBinding
import com.eazy.stcbusiness.model.TransportationTypeModel
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationDestinationActivity.Companion.ADDRESS
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationDestinationActivity.Companion.LATITUDE
import com.eazy.stcbusiness.ui.transportation.booknow.TransportationDestinationActivity.Companion.LONGITUDE
import com.eazy.stcbusiness.ui.transportation.bookschedule.DateBottomSheetDialogFragment
import com.eazy.stcbusiness.utils.Utils
import com.eazy.stcbusiness.utils.maps.GPSTracker
import com.eazy.stcbusiness.view_model.OnClickBackListener
import com.eazy.stcbusiness.view_model.TransportationBookingNowViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TransportationBookNowActivity : BaseActivity<ActivityTransportationBookNowBinding, TransportationBookingNowViewModel>(),
    OnClickBackListener, OnMapReadyCallback {

    override val layoutId = R.layout.activity_transportation_book_now
    override val mViewModel: TransportationBookingNowViewModel by viewModels()

    companion object {

        fun gotoTransportationBookNowActivity(activity: Context){
            val intent = Intent(activity, TransportationBookNowActivity::class.java)
            activity.startActivity(intent)
        }

        fun gotoTransportationBookNowActivity(activity: Context, mAction : String){
            val intent = Intent(activity, TransportationBookNowActivity::class.java)
            intent.putExtra(ACTION_TYPE, mAction)
            activity.startActivity(intent)
        }

        fun gotoTransportationBookNowActivity(
            activity: Activity,
            action : String,
            activityResult: BetterActivityResult.OnActivityResult<ActivityResult>,
        ) {
            val intent = Intent(activity, TransportationBookNowActivity::class.java)
            intent.putExtra(ACTION, action)
            if (activity is SampleBaseActivity) {
                activityResult.let {
                    activity.activityLauncher.launch(intent, activityResult)
                }
            }
        }

        const val ACTION = "ACTION"

        const val ACTION_TYPE = "ACTION_TYPE"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var geocoder: Geocoder
    private var mGetLatLng : LatLng ?= null
    private var mAddress : String ?= null
    private var mDate : Date? = null
    private var mIsBookSchedule = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()

//        val mapFragment = (supportFragmentManager
//            .findFragmentById(R.id.google_map) as SupportMapFragment?)!!
//        mapFragment.getMapAsync(this)


        initGoogleMap(savedInstanceState)

        callLocation()

        geocoder = Geocoder(this, Locale.getDefault())

    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        mBinding.mapview.onCreate(savedInstanceState)
        mBinding.mapview.onResume()
        mBinding.mapview.getMapAsync(this)
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
    private fun initViewModel() {
        mViewModel.bind(this)

        mViewModel.setContext(this)

        setVariable(BR.viewModel, mViewModel)

        // Define Screen Confirm or only destination
        mViewModel.setIsDestinationAction(!TextUtils.isEmpty(getStringExtra(ACTION, this)))

        mViewModel.mValidButton.set(!TextUtils.isEmpty(getStringExtra(ACTION, this)))

        // Type
        mIsBookSchedule = !TextUtils.isEmpty(getStringExtra(ACTION_TYPE, this))
        mViewModel.mIsShowSchedule.set(mIsBookSchedule)

        mViewModel.mTitle.set(if (mIsBookSchedule)
            resources.getString(R.string.book_schedule) else resources.getString(R.string.book_now))

        mDate = Date()
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

    override fun onClickConfirmButton(isDestinationAction: Boolean) {
        // Confirm for set destination
        if (isDestinationAction) {
            val mIntent = Intent()
            mIntent.putExtra(LATITUDE,  mGetLatLng?.latitude ?: 0.0)
            mIntent.putExtra(LONGITUDE,  mGetLatLng?.longitude ?: 0.0)
            mIntent.putExtra(ADDRESS,  mAddress ?: "")
            setResult(RESULT_OK, mIntent)
            finish()
        }
        // Select type
        else {
            val mTransportationSelectTypeBottomSheetFragment = TransportationSelectTypeBottomSheetFragment()
            mTransportationSelectTypeBottomSheetFragment.initListener(object :
                TransportationSelectTypeBottomSheetFragment.OnClickCallBackListener {
                override fun onCallBackItemListener(mTransportationTypeModel: TransportationTypeModel) {
                    TransportationConfirmCheckOutActivity.gotoTransportationConfirmCheckOutActivity(
                        this@TransportationBookNowActivity
                    )
                }

            })
            mTransportationSelectTypeBottomSheetFragment.show(supportFragmentManager, TransportationSelectTypeBottomSheetFragment::class.java.name)
        }
    }

    override fun onClickSchedule() {
        val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFmt = SimpleDateFormat("hh:mm a", Locale.getDefault())
        DateBottomSheetDialogFragment.newInstance(mDate ?: Date(), object :
            DateBottomSheetDialogFragment.SelectDateListener {
            override fun selectDateListener(date: Date) {
                mDate = date
                mViewModel.mScheduleTxt.set(String.format("%s (%s)", fmt.format(date), timeFmt.format(date)))

                // Enable button
                mViewModel.mValidButton.set(!TextUtils.isEmpty(mViewModel.mAddressDestination.get()) && !mViewModel.mAddressDestination.get().equals(resources.getString(R.string.where_to)))
            }

        }).show(supportFragmentManager, DateBottomSheetDialogFragment::class.java.name)
    }

    override fun onClickCallBack() {
        TransportationDestinationActivity.gotoTransportationDestinationActivity(this,
            object : BetterActivityResult.OnActivityResult<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if (result.resultCode == RESULT_OK) {
                        val intent = result.data
                        if (intent != null && intent.hasExtra(LATITUDE)) {
                            mViewModel.mLatDestination.set(intent.getDoubleExtra(LATITUDE, 0.0))
                            mViewModel.mLongDestination.set(intent.getDoubleExtra(LONGITUDE, 0.0))
                            mViewModel.mAddressDestination.set(intent.getStringExtra(ADDRESS))

                            if (mIsBookSchedule) {
                                mViewModel.mValidButton.set(!TextUtils.isEmpty(mViewModel.mScheduleTxt.get()) &&
                                        !mViewModel.mScheduleTxt.get().equals(resources.getString(R.string.when_trip)))
                            } else {
                                mViewModel.mValidButton.set(true)
                            }
                        }
                    }
                }

            })
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

            mViewModel.mAddressPickUp.set(String.format("%s", mAddress))

        }
    }

    private fun zoomMap(lat: Double, lng: Double) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
    }

}