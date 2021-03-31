package com.market.catchprice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.market.catchprice.View.HomeFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setting_bottomnavigation_click();
    }

    void init(){
        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setItemIconTintList(null);

        homeFragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();

    }

    void setting_bottomnavigation_click(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        if(homeFragment==null){
                            homeFragment=new HomeFragment();
                        }
                        getSupportFragmentManager().beginTransaction().show(homeFragment).commit();
                        return true;
                    case R.id.auction:
                        return true;
                    case R.id.chat:
                        return true;
                    case R.id.mypage:
                        return true;
                }
                return false;
            }
        });
    }
}