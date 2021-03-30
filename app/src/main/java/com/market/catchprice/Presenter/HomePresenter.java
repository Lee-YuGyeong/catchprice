package com.market.catchprice.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.market.catchprice.Contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {
    Context context;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public HomePresenter(Context context){
        this.context=context;
        prefs=context.getSharedPreferences("mine", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }


    @Override
    public void getting_myaddress_from_sharedpreference() {

    }


    @Override
    public void getmylocation() {

    }

}
