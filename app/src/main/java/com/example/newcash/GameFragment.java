package com.example.newcash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.newcash.News.adapter.NewsAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Locale;


public class GameFragment extends Fragment {

    public GameFragment() {}
    private FragmentManager fragmentManager;

    public ViewPager pager;
    private CirclePageIndicator indicator;
    private TextView game2start, count_text;


    private int MILLISINFUTURE = 1000;
    private int COUNT_DOWN_INTERVAL = 200;
    private int count = 10;
    private CountDownTimer countDownTimer;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_main, container, false);

        pager = view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);

        game2start = view.findViewById(R.id.game2_start);
        count_text = view.findViewById(R.id.count_text);


        PagerAdapter pagerAdapter = new NewsAdapter(getFragmentManager(), 3);
        pager.setAdapter(pagerAdapter);
        indicator.setRadius(15);
        indicator.setViewPager(pager);

        indicator.setFillColor(Color.parseColor("#000000"));
        indicator.setStrokeColor(Color.parseColor("#cccccc"));




        return view;
    }


    //
    public void countDownTimer(){


        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            public void onTick(long millisUntilFinished) {
                count_text.setText(String.format(Locale.getDefault(), "%d sec.", millisUntilFinished / 1000L));
            }
            public void onFinish() {
                count_text.setText("Done.");
            }
        };
    }





    //
    public void next(int i) {

        if (i == 0) {

            pager.setCurrentItem(0, true);

        } else if (i == 1) {

            pager.setCurrentItem(1, true);

        } else if (i == 2) {

            pager.setCurrentItem(2, true);
        }
    }
}