package com.parm.eventmaker;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by alman on 2017-12-07.
 */

public class GPSActivity extends Service implements LocationListener {


    private final Context context;

    private boolean isEnabled = false;
    private boolean canGetLocation = false;
    private boolean canConnect = false;
    private final String TAG = this.getClass().getSimpleName();

    private Location location;
    private LocationManager locationManager;
    double lat;
    double lon;


    public GPSActivity(Context context) {
        this.context = context;
        getLocation();
    }


    public Location getLocation() {
        try {
            locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);

            isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            canConnect = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isEnabled && canConnect) {
                this.canGetLocation = true;

                if(canConnect){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            10, 60000, this);
                    if(locationManager !=null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                        if (location != null) {
                            lon = location.getLongitude();
                            lat = location.getLatitude();
                        }
                    }
                }

                if(isEnabled){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            10, 60000, this);
                    if(locationManager !=null) {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                        if (location != null) {
                            lon = location.getLongitude();
                            lat = location.getLatitude();
                        }
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return location;
    }


    public double getLat() {
        if(location != null){
            this.lat = location.getLatitude();
        }
        return this.lat;
    }

    public double getLon(){
        if(location != null){
            this.lon = location.getLongitude();
        }
        return this.lon;
    }

    public boolean canGetLocation(){
        return this.canGetLocation;
    }

    public void promptSettings() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("GPS Settings");
        alertDialog.setMessage("GPS not Enabled, Please enable");

        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog.show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
