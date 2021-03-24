package com.market.catchprice.Presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.market.catchprice.Contract.LoginContract;
import com.market.catchprice.MainActivity;
import com.market.catchprice.Model.LoginData;
import com.market.catchprice.Model.LoginResponse;
import com.market.catchprice.Network.ApiClient;
import com.market.catchprice.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void check_null(EditText id, EditText pw) {
        if (id.getText().toString().equals("")) view.show_null("아이디", false);
        else if (pw.getText().toString().equals("")) view.show_null("패스워드", false);
        else view.show_null("ture", true);
    }

    @Override
    public void check_login(EditText id, EditText pw) {

        LoginData loginData = new LoginData(id.getText().toString(), pw.getText().toString());

        ApiInterface ApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = ApiInterface.Login(loginData);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    view.show_login(loginResponse.getToken(), true);

                } else { //response 실패
                    view.show_login("false", false);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                view.show_login("false", false);
            }
        });
    }
}
