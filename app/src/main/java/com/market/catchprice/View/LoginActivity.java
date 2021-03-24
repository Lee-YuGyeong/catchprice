package com.market.catchprice.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.market.catchprice.Contract.LoginContract;
import com.market.catchprice.Presenter.LoginPresenter;
import com.market.catchprice.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText id;
    private EditText pw;
    private Button btn_login;
    private Button btn_signup;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Catchprice);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        init();
    }

    private void init() {
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);


        btn_login = (Button) findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.check_null(id, pw);
            }
        });

        btn_signup = (Button) findViewById(R.id.signup_btn);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void show_null(String str,boolean b) {
        if(b){
            presenter.check_login(id,pw);
        }else{
            Toast.makeText(getApplicationContext(), str + "를 입력해주세요.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void show_login(String str, boolean b) {
        if(b){
            SharedPreferences sharedPreferences = getSharedPreferences("mine", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("token", str);
            editor.commit();

        }else{
            Toast.makeText(getApplicationContext(), "아이디나 패스워드가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
