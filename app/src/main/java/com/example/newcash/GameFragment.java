package com.example.newcash;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.newcash.Game.GameB_Activity;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.securepreferences.SecurePreferences;
import com.viewpagerindicator.CirclePageIndicator;

//
import com.google.android.gms.ads.rewarded.RewardedAd;
//

public class GameFragment extends Fragment {
    public GameFragment() {}
    private FragmentManager fragmentManager;

    public ViewPager pager;
    private CirclePageIndicator indicator;
    private TextView game1start, game2start, game3start, tvStepCount, watchbtn, popCount, game_a_coin;

    private AlertDialog dialogPopup2;

    private ProgressBar progressBar;

    private int MILLISINFUTURE = 4 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer countDownTimer;

    //
    private RewardedAd gameOverRewardedAd;
    private RewardedAd extraCoinsRewardedAd;
    private RewardedAd rewardedAd;
    //
    private ImageView game2_on1, game2_on2, game2_on3;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private int gameB;
    //////////
    private InterstitialAd mInterstitialAd;
    private Button refresh;
    private CheckBox startVideoAdsMuted;
    private TextView videoStatus;
    private UnifiedNativeAd nativeAd;
    //////////

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_main, container, false);
        fragmentManager = getFragmentManager();

        game1start = view.findViewById(R.id.game1start);
        game2start = view.findViewById(R.id.game2start);
        tvStepCount = view.findViewById(R.id.tvStepCount);
        watchbtn = view.findViewById(R.id.watchbtn);
        game_a_coin = view.findViewById(R.id.game_a_coin);

        game2_on1 = view.findViewById(R.id.game2_on1);
        game2_on2 = view.findViewById(R.id.game2_on2);
        game2_on3 = view.findViewById(R.id.game2_on3);


        sharedPref = new SecurePreferences(getActivity(), "fncm0417", "fncm0417");
        editor = sharedPref.edit();

        gameCountSet();



        /////전면광고/////
//        MobileAds.initialize(getActivity(), "ca-app-pub-5646098133984483/3530392490");
//        mInterstitialAd = new InterstitialAd(getActivity());
//        mInterstitialAd.setAdUnitId("ca-app-pub-5646098133984483/3530392490");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        ///////////

        //게임A_시작버튼
        game1start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(100 > Integer.parseInt(tvStepCount.getText().toString())){
                dialog_game_a_none();
            }else {
                dialogPopup();
            }

            }
        });
        //게임시작버튼2
        game2start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameB != 0) {
                    dialogPopup2();
                }else {
                    game2_end();
                }
            }
        });
        progressBar = view.findViewById(R.id.game_a_progress);

        //동영상광고
//        rewardedAd = new RewardedAd(getActivity(), "ca-app-pub-5646098133984483/6662341344");
//        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
//            @Override
//            public void onRewardedAdLoaded() {
//                // Ad successfully loaded.
//            }
//
//            @Override
//            public void onRewardedAdFailedToLoad(int errorCode) {
//                // Ad failed to load.
//            }
//        };
//        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);

        return view;
    }


    //팝업_game1_none
    public void dialog_game_a_none() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog_game_a_none = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup_game1_none, null);

        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.game_a_back);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_game_a_none.dismiss();
            }
        });

        dialog_game_a_none.setView(view);
        dialog_game_a_none.show();

        Window window = dialog_game_a_none.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    //팝업1
    public void dialogPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialogPopup = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup_game1, null);

        //만보기 캐시
        game_a_coin = view.findViewById(R.id.game_a_coin);
        double step = Integer.parseInt(tvStepCount.getText().toString()) * 0.01;
        game_a_coin.setText((int) step + "");

        //시청버튼
        TextView watchbtn = view.findViewById(R.id.watchbtn);
        watchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (rewardedAd.isLoaded()) {
//                    Activity activityContext = null;
//                    RewardedAdCallback adCallback = new RewardedAdCallback() {
//                        public void onRewardedAdOpened() {
//                            // Ad opened.
//                        }
//
//                        public void onRewardedAdClosed() {
//                            // Ad closed.
//                            Intent intent = new Intent(getActivity(), GameA_Activity.class);
//                            startActivity(intent);
//
//                            //버튼 비활성화
//                            game1start.setEnabled(false);
//                            game1start.setBackgroundColor(Color.parseColor("#cccccc"));
//                        }
//
//                        public void onUserEarnedReward(@NonNull RewardItem reward) {
//                            // User earned reward.
//                        }
//
//                        public void onRewardedAdFailedToShow(int errorCode) {
//                            // Ad failed to display
//                        }
//                    };
//                    rewardedAd.show(activityContext, adCallback);
//                } else {
//                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
//                }

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
        dialogPopup2 = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup_game2, null);

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

//       public void fullAd() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        final AlertDialog dialogPopup = builder.create();
//
//           if (rewardedAd.isLoaded()) {
//               Activity activityContext = null;
//               RewardedAdCallback adCallback = new RewardedAdCallback() {
//                   public void onRewardedAdOpened() {
//                       // Ad opened.
//                   }
//
//                   public void onRewardedAdClosed() {
//                       // Ad closed.
//                       Intent intent = new Intent(getActivity(), GameB_Activity.class);
//                       startActivity(intent);
//
//                       mInterstitialAd.show();
//                   }
//
//                   public void onUserEarnedReward(@NonNull RewardItem reward) {
//                       // User earned reward.
//                   }
//
//                   public void onRewardedAdFailedToShow(int errorCode) {
//                       // Ad failed to display
//                   }
//               };
//               rewardedAd.show(activityContext, adCallback);
//           } else {
//               Log.d("TAG", "The rewarded ad wasn't loaded yet.");
//           }
//
//
////        mInterstitialAd.show();
//
////        dialogPopup.show();
////
////        Window window = dialogPopup.getWindow();
////        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
////        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//    }

    private void countDownTimer() {

        countDownTimer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {

                popCount.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {

                // 오류 났엇음
                Intent intent = new Intent(getActivity(), GameB_Activity.class);
                getActivity().startActivityForResult(intent, 1111);
                dialogPopup2.dismiss();

//                fullAd();
            }
        }.start();
    }

    // 만들어서 한다
    public void set(String a) {

        if(a.equals("")){

            tvStepCount.setText(0+"");
            progressBar.setProgress(0);
        } else {

            tvStepCount.setText(a);
            progressBar.setProgress(Integer.parseInt(a));
        }
    }

    //game2_end_popup
    public void game2_end() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog game2_end = builder.create();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup_game2_end, null);

        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.game_b_back);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game2_end.dismiss();
            }
        });

        game2_end.setView(view);
        game2_end.show();

        Window window = game2_end.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    //게임2_횟수3번
    public void gameCountSet() {

        gameB = sharedPref.getInt("gameB", 3);

        if(gameB == 2){

            game2_on1.setImageResource(R.drawable.game_b_end);

        } else if (gameB == 1){

            game2_on1.setImageResource(R.drawable.game_b_end);
            game2_on2.setImageResource(R.drawable.game_b_end);

        }else if (gameB == 0){

            game2_on1.setImageResource(R.drawable.game_b_end);
            game2_on2.setImageResource(R.drawable.game_b_end);
            game2_on3.setImageResource(R.drawable.game_b_end);
        }
    }
}