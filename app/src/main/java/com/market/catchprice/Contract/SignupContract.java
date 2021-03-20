package com.market.catchprice.Contract;

public interface SignupContract {
    interface View{
        void init();
    }
    interface Presenter{
        void check_duplicate_email(String email);
        void check_duplicate_nickname(String nickname);
        void check_equality_password(String pw1, String pw2);
    }
}
