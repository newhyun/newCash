package com.example.newcash;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class CashListActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private LinearLayout page_back;
    //adMob
    private AdView adView;
    //adMob


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashlist);
        fragmentManager = getSupportFragmentManager();

        page_back = findViewById(R.id.page_back);

        //adMob start
        MobileAds.initialize(this, "ca-app-pub-5646098133984483~9153968853");
        //adMob BANNER
        AdSize adSize = new AdSize(300, 50);

        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);
        //adMob end

        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}

