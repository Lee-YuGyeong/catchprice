package com.market.catchprice.Presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.market.catchprice.Contract.SignupContract;
import com.market.catchprice.Model.SignupDuplicate;
import com.market.catchprice.Model.SignupInfo;
import com.market.catchprice.Model.SignupModel;
import com.market.catchprice.Network.ApiClient;
import com.market.catchprice.Network.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter implements SignupContract.Presenter{
    ApiInterface myAPI;
    int isDuplicate;
    boolean flag;
    Context context;
    private SignupModel signupModel;
    String emailValidation="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public SignupPresenter(Context context){
        myAPI=ApiClient.getClient().create(ApiInterface.class);
        this.context=context;
        signupModel=new SignupModel(context);
    }
    @Override
    public int check_duplicate_email(String email) {
        isDuplicate=0;
        if(!email.matches(emailValidation)){
            Toast.makeText(context, "제대로 된 이메일 형식을 적어주세요", Toast.LENGTH_SHORT).show();
            return -3;
        }
        SignupDuplicate signupDuplicate=new SignupDuplicate(email, null);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Call<Object> check_email=myAPI.check_duplicate_email(signupDuplicate);//for synchronous retrofit
                    String get=check_email.execute().body().toString();
                    JSONObject duplicate_ret=new JSONObject(get);
                    if(duplicate_ret.getBoolean("IsDuplicate")){
                        isDuplicate=2;
                    }
                    else{
                        isDuplicate=1;
                    }
                }
                catch(Exception e){
                    isDuplicate=-1;
                }
            }
        }).start();
        while(isDuplicate==0);//for thread synchronization
        return isDuplicate;
    }

    @Override
    public int check_duplicate_nickname(String nickname) {
        isDuplicate=0;
        flag=true;
        if(TextUtils.isEmpty(nickname)||nickname==null||nickname==""||nickname==" "){
            Toast.makeText(context, "닉네임을 적어주세요", Toast.LENGTH_SHORT).show();
            return -3;
        }
        SignupDuplicate signupDuplicate=new SignupDuplicate(null, nickname);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Call<Object> check_nickname= myAPI.check_duplicate_nickname(signupDuplicate);//for synchronous retrofit
                    String get=check_nickname.execute().body().toString();
                    JSONObject duplicate_ret=new JSONObject(get);
                    if(duplicate_ret.getBoolean("IsDuplicate")){
                        isDuplicate=2;
                    }
                    else{
                        isDuplicate=1;
                    }
                }
                catch(Exception e) {
                    isDuplicate=-1;
                }
            }
        }).start();
        while(isDuplicate==0);//for thread synchronization
        return isDuplicate;

    }

    @Override
    public boolean check_validation(int email, int pw, int repw, int nickname, int gender, int agreement) {
        if(email==0){
            Toast.makeText(context, "이메일 중복확인을 해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pw==0){
            Toast.makeText(context, "비밀번호 형식을 확인해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(repw==0){
            Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nickname==0){
            Toast.makeText(context, "닉네임 중복확인을 해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(gender==-1){
            Toast.makeText(context, "성별을 선택해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(agreement==1){
            Toast.makeText(context, "개인정보 수집 이용에 동의해주세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public int signup(String email, String password, String name, String nickname, String birthday, boolean gender, int agreement) {
        if(TextUtils.isEmpty(email)||email==null||email==""||email==" "){
            Toast.makeText(context, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(TextUtils.isEmpty(password)||password==null||password==""||password==" "){
            Toast.makeText(context, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(TextUtils.isEmpty(name)||name==null||name==""||name==" "){
            Toast.makeText(context, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(TextUtils.isEmpty(nickname)||nickname==null||nickname==""||nickname==" "){
            Toast.makeText(context, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(TextUtils.isEmpty(birthday)||birthday==null||birthday==""||birthday==" "){
            Toast.makeText(context, "생년월일을 입력해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        if(agreement==0){
            Toast.makeText(context, "개인정보 수집 이용 동의를 확인해주세요", Toast.LENGTH_SHORT).show();
            return -1;
        }
        return signupModel.signup(nickname, email, password, name, birthday, gender);
    }


}
