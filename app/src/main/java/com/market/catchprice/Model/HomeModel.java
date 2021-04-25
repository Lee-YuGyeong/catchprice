package com.market.catchprice.Model;

import com.market.catchprice.Contract.HomeContract;
import com.market.catchprice.Network.ApiClient;
import com.market.catchprice.Network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements HomeContract.Model {

    @Override
    public void getTodayAutionList() {

        AutionData autionData = new AutionData();

        ApiInterface ApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AutionResponse> call = ApiInterface.autionList(autionData);

        call.enqueue(new Callback<AutionResponse>() {
            @Override
            public void onResponse(Call<AutionResponse> call, Response<AutionResponse> response) {

                if (response.isSuccessful()) {
                    AutionResponse autionResponse = response.body();
                    //view.show_login(loginResponse.getToken(), true);

                } else {
                    //view.show_login("false", false);
                }

            }

            @Override
            public void onFailure(Call<AutionResponse> call, Throwable t) {
                //view.show_login("false", false);
            }
        });

    }
}
