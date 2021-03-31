package com.market.catchprice.View;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.market.catchprice.Contract.HomeContract;
import com.market.catchprice.MainActivity;
import com.market.catchprice.Presenter.HomePresenter;
import com.market.catchprice.R;

public class HomeFragment extends Fragment implements HomeContract.View {
    MainActivity mainActivity;
    private HomePresenter homePresenter;
    private static final int GPS_ENABLE_REQUEST_CODE=1002;
    private static final int PERMISSIONS_REQUEST_CODE=1001;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity=(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity=null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);

        init(v);
        //initializing UI
        homePresenter=new HomePresenter(mainActivity);
        //initializing HomePresenter

        getting_myaddress();
        setting_mylocation_btn();
        setting_plus_floating_btn();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void init(View v) {

    }

    @Override
    public void setting_mylocation_btn() {

    }

    @Override
    public void setting_plus_floating_btn() {
        //getlocationpermission() 쓸 예정
    }

    @Override
    public void getting_myaddress() {
        String myaddress=homePresenter.getting_myaddress_from_sharedpreference();
    }


    /***check user's phone location permission***/
    @Override
    public void getlocationpermission() {
        if(Build.VERSION.SDK_INT<23){
            homePresenter.getmylocation();
        }
        if(!check_Myphone_LocationServicesStatus()){
            showDialogForLocationServiceSetting();
        }else{
            check_App_RunTimePermission();
        }
    }

    /***check user's phone GPS and NETWORK state***/
    @Override
    public boolean check_Myphone_LocationServicesStatus() {
        LocationManager locationManager=(LocationManager)mainActivity.getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                ||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /***if user didn't activate GPS service, make user to activate GPS service***/
    @Override
    public void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("위치 사용을 위해서는 위치 서비스 활성화가 필요합니다.\n"
        +"위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPSSettingIntent
                        =new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    /***after checking user's phone's permission, check app location permission***/
    @Override
    public void check_App_RunTimePermission() {
        int permissioncoarse= ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionfine=ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissioncoarse== PackageManager.PERMISSION_DENIED||permissionfine==PackageManager.PERMISSION_DENIED){
            if(shouldShowRequestPermissionRationale(REQUIRED_PERMISSIONS[0])){
                Toast.makeText(mainActivity, "위치 권한 설정이 필요합니다.", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                }catch (Exception e){e.printStackTrace();}
                requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }else{
                requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        }else{
            homePresenter.getmylocation();
        }
    }

    /***after checking user's phone location permission and give dialog by showDialogForLocationServiceSetting***/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==mainActivity.RESULT_OK){
            switch(requestCode){
                case GPS_ENABLE_REQUEST_CODE:
                    if(check_Myphone_LocationServicesStatus()){
                        check_App_RunTimePermission();
                        return;
                    }
                    break;
            }
        }
    }

    /***after runtime checking location permission***/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSIONS_REQUEST_CODE&&grantResults.length==REQUIRED_PERMISSIONS.length){
            boolean check_result=true;
            for(int result: grantResults){
                if(result!=PackageManager.PERMISSION_GRANTED){
                    check_result=false;
                    break;
                }
            }
            if(check_result){
                Toast.makeText(mainActivity, "위치 권한이 설정되었습니다.", Toast.LENGTH_SHORT).show();
            }
            else{
                AlertDialog.Builder builder=new AlertDialog.Builder(mainActivity);
                builder.setTitle("알림");
                builder.setMessage("[설정]->[권한]에서\n권한을 허용해주세요.\n");
                builder.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        }
    }
}
