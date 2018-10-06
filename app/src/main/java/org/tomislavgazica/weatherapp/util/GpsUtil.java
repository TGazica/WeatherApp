package org.tomislavgazica.weatherapp.util;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import org.tomislavgazica.weatherapp.App;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class GpsUtil {

    private Context context;
    private Activity activity;
    private static final int REQUEST_LOCATION_PERMISSION = 100;
    private GpsListener gpsListener;

    public GpsUtil(Context context, Activity activity, GpsListener gpsListener) {
        this.context = context;
        this.activity = activity;
        this.gpsListener = gpsListener;
        getLastLocation();
    }

    private void onLocationChanged(Location location) {
        App.setLatitude(location.getLatitude());
        App.setLongitude(location.getLongitude());
        gpsListener.onLocationSuccess();
    }

    private void getLastLocation() {
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(context);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                onLocationChanged(location);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            gpsListener.onLocationFail();
                        }
                    });
        } else {
            gpsListener.onLocationFail();
        }
    }
}