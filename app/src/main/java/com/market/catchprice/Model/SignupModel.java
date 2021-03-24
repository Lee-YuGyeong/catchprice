package com.market.catchprice.Model;

import android.content.Context;
import android.widget.Toast;

import com.market.catchprice.Network.ApiClient;
import com.market.catchprice.Network.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupModel {
    ApiInterface myAPI;
    Context context;

    public SignupModel(Context context){
        myAPI= ApiClient.getClient().create(ApiInterface.class);
        this.context=context;
    }

    public int signup(String nickname, String email, String password, String name, String birthday, boolean gender){
        final int[] isSuccess = {0};
        SignupInfo user=new SignupInfo(nickname, email, password, name, birthday, gender);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Call<Void> user_signup=myAPI.signup(user);
                    user_signup.execute();
                    isSuccess[0]=1;
                } catch (IOException e) {
                    isSuccess[0]=2;
                }
            }
        }).start();
        while(isSuccess[0]==0);
        return isSuccess[0];
    }

}
