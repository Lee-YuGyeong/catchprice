package com.market.catchprice.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.market.catchprice.Contract.LoginContract;
import com.market.catchprice.R;

public class LoginView extends AppCompatActivity {

    private EditText id;
    private EditText pw;
    private Button btn_login;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Catchprice);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        btn_login = (Button) findViewById(R.id.login_btn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 페이지로 넘어가기
            }
        });
    }
}
