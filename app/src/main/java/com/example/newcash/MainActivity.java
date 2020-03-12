package com.example.newcash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements SensorEventListener{

    private FragmentManager fragmentManager;

    private LinearLayout gameNav, shoppingNav, moreNav;
    private RelativeLayout cashBox;

    private TextView game1start;


    //만보기
    private SensorManager sensorManager;
    private Sensor stepCountSensor;



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

        if(stepCountSensor == null) {
            Toast.makeText(this, "No Step Detect Sensor", Toast.LENGTH_SHORT).show();
        }
        //리셋
        long now = System.currentTimeMillis();
        Date date = new Date(now);

        SimpleDateFormat setHour = new SimpleDateFormat("HH");
        SimpleDateFormat setMin = new SimpleDateFormat("mm");
        SimpleDateFormat setSec = new SimpleDateFormat("ss");

        String HH = setHour.format(date);
        String mm = setMin.format(date);
        String ss = setSec.format(date);
        //



        // 시작 할 때 바로 호출
        fragmentManager.beginTransaction().replace(R.id.main_container, new GameFragment()).commit();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.game_nav:

                        // Fragment 이동시 R.id.main_container == FrameLayout을 지정
                        // 이곳에 계속 프래그먼트를 올려서 빠르게 빠르게 바꿈
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


    //만보기기
   @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(this);
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

}

