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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.market.catchprice.Adapter.AutionAdapter;
import com.market.catchprice.Adapter.TodayAutionAdapter;
import com.market.catchprice.Contract.HomeContract;
import com.market.catchprice.MainActivity;
import com.market.catchprice.Model.AutionResponse;
import com.market.catchprice.Model.TodayAutionResponse;
import com.market.catchprice.Presenter.HomePresenter;
import com.market.catchprice.R;

public class HomeFragment extends Fragment implements HomeContract.View {
    MainActivity mainActivity;
    private HomePresenter homePresenter;
    private static final int GPS_ENABLE_REQUEST_CODE = 1002;
    private static final int PERMISSIONS_REQUEST_CODE = 1001;
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    String mylocation;

    RecyclerView recyclerView;
    RecyclerView gridView;
    TodayAutionAdapter todayAutionAdapter;
    AutionAdapter autionAdapter;
    Spinner spinner;
    ImageView gps;
    TextView mylocation_text;
    ImageView upload_btn;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        homePresenter = new HomePresenter(mainActivity);
        //initializing HomePresenter
        init(v);
        //initializing UI

        setting_mylocation_btn();
        setting_plus_floating_btn();

        setting_recyclerView();
        setting_gridView();
        click_spinner();

        return v;
    }

    public void init(View v) {

        gps = v.findViewById(R.id.gps);
        Glide.with(mainActivity).load(R.drawable.gps_btn).into(gps);
        mylocation_text = v.findViewById(R.id.mylocation_text);
        mylocation = homePresenter.getting_myaddress_from_sharedpreference();
        mylocation_text.setText(mylocation);
        upload_btn = v.findViewById(R.id.upload_btn);
        Glide.with(mainActivity).load(R.drawable.product_upload_btn).into(upload_btn);


        recyclerView = v.findViewById(R.id.recyclerView);
        gridView = v.findViewById(R.id.gridView);
        spinner = v.findViewById(R.id.spinner);

    }

    @Override
    public void setting_recyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerRightDecoration spaceDecoration = new RecyclerRightDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        todayAutionAdapter = new TodayAutionAdapter(getContext());

        todayAutionAdapter.addItem(new TodayAutionResponse("1", "1", "1", "1"));
        todayAutionAdapter.addItem(new TodayAutionResponse("2", "2", "2", "2"));
        todayAutionAdapter.addItem(new TodayAutionResponse("3", "3", "3", "3"));

        todayAutionAdapter.setOnItemClickListener(new TodayAutionAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(TodayAutionAdapter.MyViewHolder holder, View view, int position) {
                Toast.makeText(getContext(), "????????? ?????? : " + position, Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(todayAutionAdapter);
    }

    @Override
    public void setting_gridView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridView.setLayoutManager(gridLayoutManager);

        RecyclerBottomDecoration spaceDecoration2 = new RecyclerBottomDecoration(30);
        gridView.addItemDecoration(spaceDecoration2);

        autionAdapter = new AutionAdapter(getContext());

        autionAdapter.addItem(new AutionResponse("1", "1", "1", "1"));
        autionAdapter.addItem(new AutionResponse("2", "2", "2", "2"));
        autionAdapter.addItem(new AutionResponse("3", "3", "3", "3"));
        autionAdapter.addItem(new AutionResponse("1", "1", "1", "1"));
        autionAdapter.addItem(new AutionResponse("2", "2", "2", "2"));
        autionAdapter.addItem(new AutionResponse("3", "3", "3", "3"));

        autionAdapter.setOnItemClickListener(new AutionAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(AutionAdapter.MyViewHolder holder, View view, int position) {
                Toast.makeText(getContext(), "????????? ????????? : ", Toast.LENGTH_LONG).show();
            }
        });
        gridView.setAdapter(autionAdapter);
    }

    @Override
    public void click_spinner() {
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.home, android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemIdAtPosition(position) + " ??????", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setting_mylocation_btn() {
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getlocationpermission();
            }
        });
    }

    @Override
    public void setting_plus_floating_btn() {
        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    /***check user's phone location permission***/
    @Override
    public void getlocationpermission() {
        if (Build.VERSION.SDK_INT < 23) {
            homePresenter.getmylocation();
        }
        if (!check_Myphone_LocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        } else {
            check_App_RunTimePermission();
        }
    }

    /***check user's phone GPS and NETWORK state***/
    @Override
    public boolean check_Myphone_LocationServicesStatus() {
        LocationManager locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /***if user didn't activate GPS service, make user to activate GPS service***/
    @Override
    public void showDialogForLocationServiceSetting() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("?????? ????????? ????????????");
        builder.setMessage("?????? ????????? ???????????? ?????? ????????? ???????????? ???????????????.\n"
                + "?????? ????????? ?????????????????????????");
        builder.setCancelable(true);
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent callGPSSettingIntent
                        = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
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
        int permissioncoarse = ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionfine = ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissioncoarse == PackageManager.PERMISSION_DENIED || permissionfine == PackageManager.PERMISSION_DENIED) {
            if (shouldShowRequestPermissionRationale(REQUIRED_PERMISSIONS[0])) {
                Toast.makeText(mainActivity, "?????? ?????? ????????? ???????????????.", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            } else {
                requestPermissions(REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            mylocation = homePresenter.getmylocation();
            mylocation_text.setText(mylocation);
        }
    }

    /***after checking user's phone location permission and give dialog by showDialogForLocationServiceSetting***/
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == mainActivity.RESULT_OK) {
            switch (requestCode) {
                case GPS_ENABLE_REQUEST_CODE:
                    if (check_Myphone_LocationServicesStatus()) {
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
        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length) {
            boolean check_result = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            if (check_result) {
                Toast.makeText(mainActivity, "?????? ????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("??????");
                builder.setMessage("[??????]->[??????]??????\n????????? ??????????????????.\n");
                builder.setNegativeButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
}
