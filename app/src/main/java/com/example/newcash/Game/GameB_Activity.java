package com.example.newcash.Game;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.newcash.R;
import com.example.newcash.SaveActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class GameB_Activity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCountSensor;

    private int MILLISINFUTURE = 6 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer countDownTimer;

    private TextView main_b_count, main_b_StepCount;

    //adMob
    private AdView adView;
    //adMob

    private int aa = 0;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_b);

        main_b_count = findViewById(R.id.main_b_count);
        main_b_StepCount = findViewById(R.id.main_b_StepCount);

        //만보기
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        main_b_StepCount.setText("0");



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

    }


    @Override
    public void onSensorChanged(SensorEvent event) {


        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {

            aa++;

            main_b_StepCount.setText("" + aa);

            if(aa == 2) {
                countDownTimer();
            }
        }
    }

//    public void set(String a) {
//
//        main_b_StepCount.setText(a);
//    }

    private void countDownTimer() {

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {

                main_b_count.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(GameB_Activity.this, SaveActivity.class);
                intent.putExtra("shake_save", aa);

                startActivity(intent);

                finish();

            }
        }.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}
