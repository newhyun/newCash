package com.example.newcash;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
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
    private TextView game1start, game2start, game3start, tvStepCount, watchbtn, popCount;


    private int MILLISINFUTURE = 4 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer countDownTimer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_main, container, false);

        //
        game1start = view.findViewById(R.id.game1start);
        game2start = view.findViewById(R.id.game2start);
//        game3start = view.findViewById(R.id.game3start);
        tvStepCount = view.findViewById(R.id.tvStepCount);
        watchbtn = view.findViewById(R.id.watchbtn);

        //게임시작버튼
        game1start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogPopup();
            }
        });

        //게임시작버튼2
        game2start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogPopup2();



            }
        });


        return view;
    }


    //팝업1
    public void dialogPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialogPopup = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup, null);

        //시청버튼
        TextView watchbtn = view.findViewById(R.id.watchbtn);
        watchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity 전환

                Intent intent = new Intent(getActivity(), SaveActivity.class);
                startActivity(intent);

                dialogPopup.dismiss();
            }
        });

        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPopup.dismiss();
            }
        });

        dialogPopup.setView(view);
        dialogPopup.show();

        Window window = dialogPopup.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    //팝업2
    public void dialogPopup2() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialogPopup = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup2, null);

        popCount = view.findViewById(R.id.pop_count);


        dialogPopup.setView(view);
        dialogPopup.show();

        countDownTimer();

        Window window = dialogPopup.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);


        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPopup.dismiss();
                countDownTimer.cancel();
            }
        });
    }
    //팝업

    public void fullAd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialogPopup = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.full_ad, null);

        dialogPopup.setView(view);
        dialogPopup.show();

        Window window = dialogPopup.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    private void countDownTimer() {

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {

                popCount.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {

                fullAd();

            }
        }.start();
    }

    // 만들어서 한다
    public void set(String a) {

        tvStepCount.setText(a);
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