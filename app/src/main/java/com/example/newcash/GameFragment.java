package com.example.newcash;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
import android.os.SystemClock;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.newcash.News.adapter.NewsAdapter;
import com.example.newcash.News.mainA;
import com.example.newcash.News.mainB;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Locale;


public class GameFragment extends Fragment {

    public GameFragment() {}
    private FragmentManager fragmentManager;


    public ViewPager pager;
    private CirclePageIndicator indicator;
    private TextView game1start, game2start, game3start, tvStepCount, watchbtn, popCount;

    private AlertDialog dialogPopup2;

    private int MILLISINFUTURE = 4 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer countDownTimer;


    //////////
    private InterstitialAd mInterstitialAd, mInterstitialAd2;
    private Button refresh;
    private CheckBox startVideoAdsMuted;
    private TextView videoStatus;
    private UnifiedNativeAd nativeAd;
    //////////

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.game_main, container, false);
        fragmentManager = getFragmentManager();
        //
        game1start = view.findViewById(R.id.game1start);
        game2start = view.findViewById(R.id.game2start);
//        game3start = view.findViewById(R.id.game3start);
        tvStepCount = view.findViewById(R.id.tvStepCount);
        watchbtn = view.findViewById(R.id.watchbtn);


        /////전면광고/////
        MobileAds.initialize(getActivity(), "ca-app-pub-5646098133984483/3530392490");
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-5646098133984483/3530392490");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        ///////////



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
//                Intent intent = new Intent(getActivity(), SaveActivity.class);
//                startActivity(intent);

                dialogPopup.dismiss();
//                fragmentManager.beginTransaction().replace(R.id.main_container, new AdDialogFragment()).commit();

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
        dialogPopup2 = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup2, null);

        popCount = view.findViewById(R.id.pop_count);


        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.cancelbtn);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPopup2.dismiss();
                countDownTimer.cancel();
            }
        });

        dialogPopup2.setView(view);
        dialogPopup2.show();

        countDownTimer();

        Window window = dialogPopup2.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    //팝업

       public void fullAd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialogPopup = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.main_b, null);

        mInterstitialAd.show();

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

//                fullAd();

                dialogPopup2.dismiss();

                fragmentManager.beginTransaction().replace(R.id.main_container, new mainB()).commit();

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