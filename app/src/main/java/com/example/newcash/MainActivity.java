package com.example.newcash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    private FragmentManager fragmentManager;

    private LinearLayout gameNav, shoppingNav, moreNav;
    private RelativeLayout cashBox;

    private SensorManager sensorManager;
    private Sensor stepCountSensor;

    //adMob
    private AdView adView;
    //adMob


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        // drawer layout
        gameNav = findViewById(R.id.game_nav);
        shoppingNav = findViewById(R.id.shopping_nav);
        moreNav = findViewById(R.id.more_nav);

        //
        cashBox = findViewById(R.id.cash_box);

        //만보기
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);



        //adMob start
        MobileAds.initialize(this, "ca-app-pub-5646098133984483~9153968853");
        //adMob BANNER
        AdSize adSize = new AdSize(300, 50);

        adView = findViewById(R.id.adView);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-5646098133984483/2588560504");

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);
        //adMob end



//        if(stepCountSensor == null) {
//            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
//        }

        //시간
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat setHour = new SimpleDateFormat("HH");
        SimpleDateFormat setMin = new SimpleDateFormat("mm");
        SimpleDateFormat setSec = new SimpleDateFormat("ss");

        String HH = setHour.format(date);
        String mm = setMin.format(date);
        String ss = setSec.format(date);
        //


        //시작할때 바로 불러오기
        fragmentManager.beginTransaction().replace(R.id.main_container, new GameFragment()).commit();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.game_nav:

                        fragmentManager.beginTransaction().replace(R.id.main_container, new GameFragment()).commit();
                        cashBox.setVisibility(View.VISIBLE);
                        break;

                    case R.id.shopping_nav:

                        fragmentManager.beginTransaction().replace(R.id.main_container, new ShoppingFragment()).commit();
                        cashBox.setVisibility(View.VISIBLE);
                        break;

                    case R.id.more_nav:

                        fragmentManager.beginTransaction().replace(R.id.main_container, new MoreFragment()).commit();
                        cashBox.setVisibility(View.GONE);
                        break;
                }
            }

        };

        gameNav.setOnClickListener(onClickListener);
        shoppingNav.setOnClickListener(onClickListener);
        moreNav.setOnClickListener(onClickListener);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
//            tvStepCount.setText("Step Count : " + String.valueOf(event.values[0]));

            GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
            gameFragment.set(String.valueOf(event.values[0]).replaceAll(".0", ""));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1111) {

            if (resultCode == RESULT_OK) {

//                fragmentManager.beginTransaction().replace(R.id.main_container, new GameFragment()).commit();

                GameFragment gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
                gameFragment.gameCountSet();
            }
        }
    }


    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();

        //        sensorManager.unregisterListener(this);
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }

        super.onDestroy();
    }


}

