package com.market.catchprice.Presenter;

import android.widget.EditText;

import com.market.catchprice.Contract.LoginContract;

public class LoginPresenter implements LoginContract.Presenter {

    LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void check_null(EditText id, EditText pw) {
        if (id.getText().toString().equals("")) view.show_null("아이디");
        else if (pw.getText().toString().equals("")) view.show_null("패스워드");
    }
}
