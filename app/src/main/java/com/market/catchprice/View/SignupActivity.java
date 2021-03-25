package com.market.catchprice.View;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.market.catchprice.Contract.SignupContract;
import com.market.catchprice.Presenter.SignupPresenter;
import com.market.catchprice.R;

public class SignupActivity extends AppCompatActivity implements SignupContract.View {
    LinearLayout signup_background;
    ImageView signup_text_img, signup_btn_img, signup_agreement_img, agreement_check_img;
    LinearLayout signup_agreement;
    EditText signup_email, signup_pw, signup_repw, signup_nickname, signup_name, signup_birth;
    ImageView email_confirm, nickname_confirm;
    ImageView signup;
    TextView pw_check, pw_duplicate_check;
    TextView signup_male, signup_female;
    String email=null, pw=null, repw=null, name=null, birth=null, nickname=null;
    int email_valid=0, pw_valid=0, repw_valid=0, nickname_valid=0, gender=-1, agreement=0;
    boolean my_gender;
    String passwordValidation="^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&]).{7,}.$";
    private SignupPresenter signupPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
        //initializing UI
        check_for_password();
        check_for_duplicate_password();
        check_for_rewrite_email();
        duplicate_email_btn();
        check_for_rewrite_nickname();
        duplicate_nickname_btn();
        gender_btn();
        agreement_btn();
        signup_btn();
        //initializing clicklistener
        signupPresenter=new SignupPresenter(this);
        //initializing signuppresenter
    }

    @Override
    public void init() {
        signup_background=findViewById(R.id.signup_background);
        signup_text_img=findViewById(R.id.signup_text_img);
        signup_btn_img=findViewById(R.id.signup_btn_img);
        agreement_check_img=findViewById(R.id.agreement_check_img);
        signup_agreement_img=findViewById(R.id.signup_agreement_img);
        signup_agreement=findViewById(R.id.signup_agreement);
        signup_email=findViewById(R.id.signup_email);
        signup_pw=findViewById(R.id.signup_pw);
        signup_repw=findViewById(R.id.signup_repw);
        signup_nickname=findViewById(R.id.signup_nickname);
        signup_name=findViewById(R.id.signup_name);
        signup_birth=findViewById(R.id.signup_birth);
        email_confirm=findViewById(R.id.email_confirm);//
        nickname_confirm=findViewById(R.id.nickname_confirm);//
        signup=findViewById(R.id.signup_btn_img);
        pw_check=findViewById(R.id.pw_check_text);
        pw_duplicate_check=findViewById(R.id.pw_duplicate_check_text);
        signup_male=findViewById(R.id.signup_male);
        signup_female=findViewById(R.id.signup_female);


        Glide.with(this).load(R.drawable.signup_text).into(signup_text_img);
        Glide.with(this).load(R.drawable.checkbox).into(signup_btn_img);
        Glide.with(this).load(R.drawable.agreement_notok).into(agreement_check_img);
        Glide.with(this).load(R.drawable.agreement_text).into(signup_agreement_img);
        Glide.with(this).load(R.drawable.duplicate_text).into(email_confirm);
        Glide.with(this).load(R.drawable.duplicate_text).into(nickname_confirm);
        Glide.with(this)
                .asBitmap()
                .load(R.drawable.signup_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Drawable drawable=new BitmapDrawable(getResources(), resource);
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                            signup_background.setBackground(drawable);
                        }
                    }
                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    @Override
    public void check_for_password() {
        signup_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pw_check.setText("8자리 이상 영문, 숫자, 특수문자로 입력해 주세요");
                pw_check.setTextColor(Color.RED);
                pw_valid=0;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                pw=signup_pw.getText().toString().trim();
                if(pw.matches(passwordValidation)){
                    pw_check.setText("알맞은 비밀번호입니다");
                    pw_check.setTextColor(Color.GREEN);
                    pw_valid=1;
                }
                else{
                    pw_check.setText("8자리 이상 영문, 숫자, 특수문자로 입력해 주세요");
                    pw_check.setTextColor(Color.RED);
                    pw_valid=0;
                }
            }
        });
    }

    @Override
    public void check_for_duplicate_password() {
        signup_repw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pw_duplicate_check.setText("");
                repw_valid=0;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                repw=signup_repw.getText().toString().trim();
                if(repw.equals(pw)){
                    pw_duplicate_check.setText("비밀번호가 일치합니다");
                    pw_duplicate_check.setTextColor(Color.GREEN);
                    repw_valid=1;
                }
                else{
                    pw_duplicate_check.setText("비밀번호가 일치하지 않습니다");
                    pw_duplicate_check.setTextColor(Color.RED);
                    repw_valid=0;
                }
            }
        });
    }

    @Override
    public void duplicate_email_btn() {
        email_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=signup_email.getText().toString().trim();
                int isDuplicate=signupPresenter.check_duplicate_email(email);
                if(isDuplicate==2){
                    Toast.makeText(SignupActivity.this, "이미 등록된 이메일입니다.", Toast.LENGTH_SHORT).show();
                    signup_email.setTextColor(Color.RED);
                    email_valid=0;
                }
                else if(isDuplicate==1){
                    Toast.makeText(SignupActivity.this, "중복확인 되었습니다", Toast.LENGTH_SHORT).show();
                    signup_email.setTextColor(Color.GREEN);
                    email_valid=1;
                }
                else if(isDuplicate==-1){
                    Toast.makeText(SignupActivity.this, "오류가 발생하였습니다.\n다시 시도해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(isDuplicate==0){
                    Toast.makeText(SignupActivity.this, "서버오류가 발생하였습니다.\n관리자에게 알려주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void check_for_rewrite_email() {
        signup_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signup_email.setTextColor(Color.BLACK);
                email_valid=0;
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @Override
    public void duplicate_nickname_btn() {
        nickname_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname=signup_nickname.getText().toString().trim();
                int isDuplicate=signupPresenter.check_duplicate_nickname(nickname);
                if(isDuplicate==2){
                    Toast.makeText(SignupActivity.this, "이미 등록된 닉네임이 있습니다", Toast.LENGTH_SHORT).show();
                    signup_nickname.setTextColor(Color.RED);
                    nickname_valid=0;
                }
                else if(isDuplicate==1){
                    Toast.makeText(SignupActivity.this, "사용가능한 닉네임입니다", Toast.LENGTH_SHORT).show();
                    signup_nickname.setTextColor(Color.GREEN);
                    nickname_valid=1;
                }
                else if(isDuplicate==-1){
                    Toast.makeText(SignupActivity.this, "오류가 발생하였습니다.\n다시 시도해주세요", Toast.LENGTH_SHORT).show();
                }
                else if(isDuplicate==0){
                    Toast.makeText(SignupActivity.this, "서버오류가 발생하였습니다.\n관리자에게 알려주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void check_for_rewrite_nickname() {
        signup_nickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                signup_nickname.setTextColor(Color.BLACK);
                nickname_valid=0;
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    @Override
    public void gender_btn() {
        signup_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender=0;
                my_gender=false;
                signup_male.setTextColor(Color.BLACK);
                signup_female.setTextColor(Color.parseColor("#BDBDBD"));
            }
        });
        signup_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender=1;
                my_gender=true;
                signup_male.setTextColor(Color.parseColor("#BDBDBD"));
                signup_female.setTextColor(Color.BLACK);
            }
        });
    }

    @Override
    public void agreement_btn() {
        signup_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreement==0){
                    agreement=1;
                    Glide.with(SignupActivity.this).load(R.drawable.agreement_ok).into(agreement_check_img);
                }
                else if(agreement==1){
                    agreement=0;
                    Glide.with(SignupActivity.this).load(R.drawable.agreement_notok).into(agreement_check_img);
                }
            }
        });
    }

    @Override
    public void signup_btn() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signupPresenter.check_validation(email_valid, pw_valid, repw_valid, nickname_valid, gender, agreement)){
                    email=signup_email.getText().toString().trim();
                    pw=signup_pw.getText().toString().trim();
                    name=signup_name.getText().toString().trim();
                    nickname=signup_nickname.getText().toString().trim();
                    birth=signup_birth.getText().toString().trim();
                    int signup_success=signupPresenter.signup(email, pw, name, nickname, birth, my_gender, 1);
                    if(signup_success==1){
                        Toast.makeText(SignupActivity.this, "회원가입 되었습니다!\n환영합니다!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else if(signup_success==2){
                        Toast.makeText(SignupActivity.this, "오류가 발생하였습니다.\n다시 시도해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
