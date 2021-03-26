package com.market.catchprice.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.market.catchprice.Contract.LoginContract;
import com.market.catchprice.Presenter.LoginPresenter;
import com.market.catchprice.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText id;
    private EditText pw;
    private Button btn_login;
    Button btn_signup;
    ImageView imageView,imageView2,imageView3,login_btn_img,signup_btn_img;
    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        init();
    }

    @Override
    public void init() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        login_btn_img = (ImageView) findViewById(R.id.login_btn_img);
        signup_btn_img = (ImageView) findViewById(R.id.signup_btn_img);
        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        btn_login = (Button) findViewById(R.id.login_btn);
        btn_signup = (Button) findViewById(R.id.signup_btn);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.layout);


        Glide.with(this).load(R.drawable.text_login).into(imageView);
        Glide.with(this).load(R.drawable.inputbox).into(imageView2);
        Glide.with(this).load(R.drawable.inputbox).into(imageView3);
        Glide.with(this).load(R.drawable.checkbox).into(login_btn_img);
        Glide.with(this).load(R.drawable.text_signup).into(signup_btn_img);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.background_login)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Drawable drawable = new BitmapDrawable(getResources(), resource);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            layout.setBackground(drawable);
                        }
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) { }
                });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.check_null(id, pw);
            }
        });

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
