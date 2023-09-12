package com.eazy.stcbusiness.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Priority;
import com.eazy.stcbusiness.MainActivity;
import com.eazy.stcbusiness.R;
import com.eazy.stcbusiness.base.SampleBaseActivity;
import com.eazy.stcbusiness.utils.AppLOGG;
import com.eazy.stcbusiness.utils.maps.GPSTracker;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends SampleBaseActivity {

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback mLocationCallback;

    private final static int REQUEST_LOCATION_PERMISSION = 546;
    private final static int REQUEST_CODE_ENABLE_LOCATION = 246;

    private boolean isAlreadyOpen = false;
    private String action = "";
    private final static int SPLASH_TIME = 4 * 1000;// 2 seconds delay

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        checkBackgroundLocationPermission();

        initLocationRequest();

    }

    private void checkBackgroundLocationPermission() {
        boolean permissionAccessCoarseLocationApproved = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        buildAlertMessageNoGps();

        if (permissionAccessCoarseLocationApproved) {
            checkGps();
        } else {
            // App doesn't have access to the device's location at all. Make full request
            // for permission.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                }, REQUEST_LOCATION_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, REQUEST_LOCATION_PERMISSION);
            }
        }
    }

    private void buildAlertMessageNoGps() {
        if (locationRequest == null)
            initLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(locationSettingsResponse -> AppLOGG.INSTANCE.d("locationLoginGPSRequest", "locationSettingsResponse"));

        task.addOnFailureListener(e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ResolvableApiException resolvable = (ResolvableApiException) e;
                    resolvable.startResolutionForResult(SplashScreenActivity.this, REQUEST_CODE_ENABLE_LOCATION);
                } catch (IntentSender.SendIntentException sendEx) {
                    // Ignore the error.
                }
            }
        });
    }

    private void initLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                for (Location location : locationResult.getLocations()) {
                    AppLOGG.INSTANCE.d("locationLoginUpdate", location.getLatitude() + " : " + location.getLongitude());
                    findZipCode(location.getLatitude(), location.getLongitude());
                    break;
                }
            }
        };
    }

    private void findZipCode(final double lat, final double lng) {
        SampleBaseActivity.Companion.setLatitude(lat);
        SampleBaseActivity.Companion.setLongitude(lng);

        // -------------
        Geocoder geocoder = new Geocoder(SplashScreenActivity.this, Locale.getDefault());
        List<Address> addresses;
        try {
             addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {

                handlerActivity(!action.equalsIgnoreCase("already_front_desk") && !action.equalsIgnoreCase("already_notification"));
            }
            else {
              handlerActivity(!action.equalsIgnoreCase("already_emergency") && !action.equalsIgnoreCase("already_front_desk") && !action.equalsIgnoreCase("already_notification"));
            }
        } catch (IOException e) {

            handlerActivity(false);
        }
    }

    private void handlerActivity(boolean isShowTimer) {
        if (isShowTimer){
            try {
                new Handler().postDelayed(() -> {
                    if (!isAlreadyOpen) {
                        isAlreadyOpen = true;
                        startNewActivity();
                    }
                }, SPLASH_TIME);
            } catch(Exception e){
                if (!isAlreadyOpen) {
                    isAlreadyOpen = true;
                    startNewActivity();
                }
            }
        } else {
            if (!isAlreadyOpen) {
                isAlreadyOpen = true;
                startNewActivity();
            }
        }

    }

    private void startNewActivity() {
        MainActivity.Companion.gotoMainActivity(this);
    }

    private void checkGps() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert manager != null;
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            getLastKnowLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                checkGps();
            } else {
                Toast.makeText(SplashScreenActivity.this, "Permission location is not Grand", Toast.LENGTH_SHORT).show();
                showSettingsAlert();
            }
        }
    }

    private void goToSettingsPermission() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(myAppSettings);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ENABLE_LOCATION && resultCode == RESULT_OK) {
            requestLocationUpdate();
        }
    }

    private void getLastKnowLocation() {
        GPSTracker gpsTracker = new GPSTracker(this);
        if (gpsTracker.canGetLocation() && gpsTracker.getLatitude() != 0 && gpsTracker.getLongitude() != 0) {
            double lat = gpsTracker.getLatitude();
            double lng = gpsTracker.getLongitude();
            // logDebug("locationLoginLastKnown", lat + " : " + lng);
            findZipCode(lat, lng);
        } else {
            requestLocationUpdate();
        }
    }

    private void requestLocationUpdate() {
        if (mFusedLocationClient == null || mLocationCallback == null) {
            // If code enter this block, there is no way to solve.
//            logDebug("requestLocationUpdate", "This block happens only when user clear Location while app already granted those permissions.");
//            logDebug("locationLoginRequest", "unexpected problem" + mFusedLocationClient + "," + locationRequest + ", " + mLocationCallback);
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest,
                mLocationCallback, Looper.getMainLooper());
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeLocationUpdate();
    }

    private void removeLocationUpdate() {
        if (mFusedLocationClient != null && mLocationCallback != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }

    private void stopLocationUpdates() {
        if (mLocationCallback == null) return;
        FusedLocationProviderClient fusedLocationProviderClient = new FusedLocationProviderClient(this);
        fusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    private void startLocationUpdates() {
        if (mLocationCallback == null) return;
        mFusedLocationClient = new FusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest,
                mLocationCallback,
                Looper.myLooper());
    }

    public void showSettingsAlert(){
        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(SplashScreenActivity.this);
        alertDialog.setTitle("Permission Location is required !");
        alertDialog.setMessage("Permission Location is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Permission Settings", (dialog, which) -> goToSettingsPermission());
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}