package com.example.newcash.News;

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
import androidx.fragment.app.Fragment;
import com.example.newcash.GameFragment;
import com.example.newcash.R;


public class mainB extends Fragment  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepCountSensor;

    private int MILLISINFUTURE = 60 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer countDownTimer;


    private TextView main_b_count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_b, container, false);

        main_b_count = view.findViewById(R.id.main_b_count);

        countDownTimer();

        return view;
    }


    private void countDownTimer() {

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {

                main_b_count.setText(millisUntilFinished / 1000 + "");

                Log.d("main_b_count", millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
//            tvStepCount.setText("Step Count : " + String.valueOf(event.values[0]));

            GameFragment gameFragment = (GameFragment) getFragmentManager().findFragmentById(R.id.main_container);
            gameFragment.set(String.valueOf(event.values[1]).replaceAll(".0", ""));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
