package com.example.newcash;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class CouponActivity extends AppCompatActivity {

    //adMob
    private AdView adView;
    //adMob

    private LinearLayout page_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        //adMob start
        MobileAds.initialize(this, "ca-app-pub-5646098133984483/4286200269");
        //adMob BANNER
        AdSize adSize = new AdSize(300, 50);

        adView = findViewById(R.id.adView);
        page_back = findViewById(R.id.page_back);

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
