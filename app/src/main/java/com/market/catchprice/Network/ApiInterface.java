package com.market.catchprice.Network;

import com.market.catchprice.LoginData;
import com.market.catchprice.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @Multipart
    @POST("/api/auth/login")
    Call<LoginResponse> Login(@Body LoginData); //로그인 데이터 검색


}
