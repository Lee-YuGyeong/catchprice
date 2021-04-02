package com.market.catchprice.Contract;

public interface HomeContract {
    interface View{
        void setting_mylocation_btn();
        void setting_plus_floating_btn();
        void getlocationpermission();
        boolean check_Myphone_LocationServicesStatus();
        void showDialogForLocationServiceSetting();
        void check_App_RunTimePermission();
    }
    interface Presenter{
        String getting_myaddress_from_sharedpreference();
        String getmylocation();
    }
}
