package com.market.catchprice.Network;

import com.market.catchprice.Model.AutionData;
import com.market.catchprice.Model.AutionResponse;
import com.market.catchprice.Model.LoginData;
import com.market.catchprice.Model.LoginResponse;
import com.market.catchprice.Model.SignupDuplicate;
import com.market.catchprice.Model.SignupInfo;
import com.market.catchprice.Model.TodayAutionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;



public interface ApiInterface {
    @POST("auth/register")
    Call<Void> signup(@Body SignupInfo signupInfo);

    @PUT("auth/register")
    Call<Object> check_duplicate_email(@Body SignupDuplicate signupDuplicate);

    @PUT("auth/register")
    Call<Object> check_duplicate_nickname(@Body SignupDuplicate signupDuplicate);

    @POST("auth/login")
    Call<LoginResponse> Login(@Body LoginData loginData);

    @POST("products")
    Call<AutionResponse> autionList(@Body AutionData autionData);

}
