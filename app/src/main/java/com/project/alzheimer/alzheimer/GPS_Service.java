package com.project.alzheimer.alzheimer;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by ahmed on 3/17/2018.
 */

public class GPS_Service extends Service {
    Timer myTimer;
    String value="";
    int permiliscond=0;
   int MY_PERMISSIONS_REQUEST_SEND_SMS=1;
    private LocationListener listener;
    private LocationManager locationManager;
    private mysms ssms;
    mydatabase _mydatabase;
    String Longitude,Latitude,mylocation,num;
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        _mydatabase=new mydatabase(this);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");

                Longitude=String.valueOf(location.getLongitude()) ;
                Latitude=String.valueOf(location.getLatitude()) ;
                 num = _mydatabase.smsnum();
                if(!Longitude.trim().isEmpty()&&!Latitude.trim().isEmpty()&&!num.trim().isEmpty()) {
                    mylocation = "https://www.google.com/maps?q=" + Longitude + "," + Latitude;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.SEND_SMS)
                                == PackageManager.PERMISSION_DENIED) {


                            String[] permissions = {Manifest.permission.SEND_SMS};



                        }
                    }
                    else
                    {
                        ssms = new mysms();
                        ssms.sendSmsMsg(num, mylocation);
                    }



                }

                i.putExtra("Longitude", location.getLongitude());
                i.putExtra("Latitude", location.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
//Asmaa Mohamed Ammar.
            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        //noinspection MissingPermission
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, listener);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(locationManager != null){
            //noinspection MissingPermission
            locationManager.removeUpdates(listener);
        }
    }

    }
