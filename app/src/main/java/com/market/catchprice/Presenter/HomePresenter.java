package com.market.catchprice.Presenter;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.market.catchprice.Contract.HomeContract;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomePresenter implements HomeContract.Presenter, LocationListener {
    Context context;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    double mylat, mylon;
    Geocoder geocoder;
    public HomePresenter(Context context){
        this.context=context;
        prefs=context.getSharedPreferences("mine", Context.MODE_PRIVATE);
        editor = prefs.edit();
        geocoder=new Geocoder(this.context, Locale.KOREAN);
    }


    @Override
    public String getting_myaddress_from_sharedpreference() {
        String myaddress=prefs.getString("myaddress", "동네 설정");
        return myaddress;
    }


    @Override
    public String getmylocation() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED&&
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            if(locationManager!=null){
                Location mylocation=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if(mylocation==null){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    mylocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(mylocation!=null){
                        mylat=mylocation.getLatitude();
                        mylon=mylocation.getLongitude();
                        List<Address> myadd=null;
                        try{
                            myadd=geocoder.getFromLocation(mylat, mylon, 1);
                        }catch(IOException e){
                            Toast.makeText(context, "GPS 서비스 사용불가", Toast.LENGTH_SHORT).show();
                        }
                        if(myadd==null||myadd.size()==0){
                            Toast.makeText(context, "GPS가 불안정하여 주소를 발견하지 못했습니다. ", Toast.LENGTH_SHORT).show();
                        }else{
                            String myaddress="동네 설정";
                            String big=myadd.get(0).getAdminArea();
                            String middle=myadd.get(0).getSubLocality();
                            String small=myadd.get(0).getThoroughfare();
                            if(big!=null&&middle==null&&small==null){
                                myaddress=big;
                            }
                            if(big!=null&&middle!=null&&small==null){
                                myaddress=big+" "+middle;
                            }
                            if(big!=null&&middle==null&&small!=null){
                                myaddress=big+" "+small;
                            }
                            if(middle!=null&&small!=null){
                                myaddress=middle+" "+small;
                            }
                            editor.putString("myaddress", myaddress);
                            editor.putFloat("lat", (float)mylat);
                            editor.putFloat("lon", (float)mylon);
                            editor.commit();
                            return myaddress;
                        }
                    }
                }else{
                    mylat=mylocation.getLatitude();
                    mylon=mylocation.getLongitude();
                    List<Address> myadd=null;
                    try{
                        myadd=geocoder.getFromLocation(mylat, mylon, 1);
                    }catch(IOException e){
                        Toast.makeText(context, "GPS 서비스 사용불가", Toast.LENGTH_SHORT).show();
                    }
                    if(myadd==null||myadd.size()==0){
                        Toast.makeText(context, "GPS가 불안정하여 주소를 발견하지 못했습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        String myaddress="동네 설정";
                        String big=myadd.get(0).getAdminArea();
                        String middle=myadd.get(0).getSubLocality();
                        String small=myadd.get(0).getThoroughfare();
                        if(big!=null&&middle==null&&small==null){
                            myaddress=big;
                        }
                        if(big!=null&&middle!=null&&small==null){
                            myaddress=big+" "+middle;
                        }
                        if(big!=null&&middle==null&&small!=null){
                            myaddress=big+" "+small;
                        }
                        if(middle!=null&&small!=null){
                            myaddress=middle+" "+small;
                        }
                        editor.putString("myaddress", myaddress);
                        editor.putFloat("lat", (float)mylat);
                        editor.putFloat("lon", (float)mylon);
                        editor.commit();
                        return myaddress;
                    }
                }
                Toast.makeText(context, "네트워크 통신이 불안정합니다.", Toast.LENGTH_SHORT).show();
            }
        }
        return prefs.getString("myaddress", "동네 설정");
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }
}
