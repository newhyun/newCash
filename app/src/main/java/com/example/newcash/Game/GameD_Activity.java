package com.example.newcash.Game;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newcash.CouponActivity;
import com.example.newcash.R;
import com.example.newcash.SaveActivity;
import com.example.newcash.adapter.CouponDTO;
import com.example.newcash.adapter.Game4CashDTO;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.securepreferences.SecurePreferences;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class GameD_Activity extends AppCompatActivity {

    //adMob
    private AdView adView;
    //adMob

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private int gameD;

    private int randombox;

    private LinearLayout gift_box, game4_ok, game4_kakao_ok;

    private KonfettiView viewKonfetti;

    private TextView game4_cash;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_d);

        sharedPref = new SecurePreferences(GameD_Activity.this, "fncm0417", "fncm0417");
        editor = sharedPref.edit();
        gameD = sharedPref.getInt("gameD", 3);


        gift_box = findViewById(R.id.gift_box);

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

        gift_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_popup_game4_result();

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                if(gameD < 0){
                    editor.putInt("gameD", 0);
                    editor.apply();
                }else {
                    editor.putInt("gameD", gameD -1);
                    editor.apply();
                }

            }
        });

    }


    //팝업
    public void dialog_popup_game4_result() {

        AlertDialog.Builder builder = new AlertDialog.Builder(GameD_Activity.this);
        final AlertDialog dialog_popup_game4_result = builder.create();
        LayoutInflater inflater = GameD_Activity.this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_popup_game4_result, null);

        TextView game4_cash = view.findViewById(R.id.game4_cash);

        //랜덤
        Random random = new Random();
        int randomInt = random.nextInt(1001);

        ArrayList randomArrayList = new ArrayList();

        for (int i = 1; i <= 1000; i++) {

            if (i <= 900) {

                randomArrayList.add("1");

            } else {

                for (int j = 1; j <= 100; j++) {

                    randomArrayList.add(j + "");
                }
            }
        }

        game4_cash.setText("" + randomArrayList.get(randomInt));
        //랜덤

//        for (int i = 1; i <= 100; i++) {
//
//            randombox = (int) (Math.random() * (100 - 1 + 1) + 1);
//
//            if ( 1 <= randombox && randombox <=  2 ){
//
//                game4_cash.setText("" + randombox);
//
//            } else if ( 3 <= randombox && randombox <=  100  ){
//
//                game4_cash.setText(1);
//
//            }
//        }

        //폭죽
        KonfettiView viewKonfetti = view.findViewById(R.id.viewKonfetti);
        ImageView game_d_coin = view.findViewById(R.id.game_d_coin);

        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f,15f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(10, 5))
                .setPosition(game_d_coin.getX() + game_d_coin.getWidth(), game_d_coin.getY() + game_d_coin.getHeight())
                .burst(800);
        //폭죽

        LinearLayout game4_ok = view.findViewById(R.id.game4_ok);
        LinearLayout game4_kakao_ok = view.findViewById(R.id.game4_kakao_ok);
        if(gameD == 1){
            game4_ok.setVisibility(View.GONE);
            game4_kakao_ok.setVisibility(View.VISIBLE);
        }

        //취소버튼
        TextView cancelbtn = view.findViewById(R.id.game_d_back);
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_popup_game4_result.dismiss();

                startActivityForResult(getIntent(), 2222);

                finish();
            }
        });

        dialog_popup_game4_result.setView(view);
        dialog_popup_game4_result.show();

        Window window = dialog_popup_game4_result.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


}