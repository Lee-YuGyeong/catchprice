package com.market.catchprice.Contract;

public interface HomeContract {
    interface View{
        void setting_mylocation_btn();
        void setting_plus_floating_btn();
        void getlocationpermission();
        boolean check_Myphone_LocationServicesStatus();
        void showDialogForLocationServiceSetting();
        void check_App_RunTimePermission();

        void setting_recyclerView();
        void setting_gridView();
        void click_spinner();
    }
    interface Presenter{
        String getting_myaddress_from_sharedpreference();
        String getmylocation();
    }
}
