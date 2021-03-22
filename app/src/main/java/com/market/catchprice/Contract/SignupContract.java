package com.market.catchprice.Contract;

public interface SignupContract {
    interface View{
        void init();
        void check_for_password();
        void check_for_duplicate_password();
        void duplicate_email_btn();
        void check_for_rewrite_email();
        void duplicate_nickname_btn();
        void check_for_rewrite_nickname();
        void gender_btn();
        void agreement_btn();
        void signup_btn();
    }
    interface Presenter{
        int check_duplicate_email(String email);
        int check_duplicate_nickname(String nickname);
        boolean check_validation(int email, int pw, int repw, int nickname, int gender, int agreement);
        int signup(String email, String password, String name, String nickname, String birthday, boolean gender, int agreement);
    }
}
