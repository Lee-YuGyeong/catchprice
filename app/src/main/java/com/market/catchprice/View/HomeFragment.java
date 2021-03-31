package com.market.catchprice.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.market.catchprice.Contract.HomeContract;
import com.market.catchprice.MainActivity;
import com.market.catchprice.Presenter.HomePresenter;
import com.market.catchprice.R;

public class HomeFragment extends Fragment implements HomeContract.View {
    MainActivity mainActivity;
    private HomePresenter homePresenter;
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
        View v=inflater.inflate(, container, false);

        init(v);
        //initializing UI
        homePresenter=new HomePresenter(mainActivity);
        //initializing HomePresenter

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

    }

    @Override
    public void getting_myaddress() {

    }


    @Override
    public void getlocationpermission() {

    }

    @Override
    public boolean check_Myphone_LocationServicesStatus() {
        return false;
    }

    @Override
    public void showDialogForLocationServiceSetting() {

    }

    @Override
    public void check_App_RunTimePermission() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
