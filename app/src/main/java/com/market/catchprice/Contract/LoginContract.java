package com.market.catchprice.Contract;

import android.widget.EditText;

public interface LoginContract {

    interface Model{

    }

    interface View{
        void show_null(String str);
    }

    interface Presenter{
        void check_null(EditText id, EditText pw);
        void check_login(EditText id, EditText pw);
    }
}
