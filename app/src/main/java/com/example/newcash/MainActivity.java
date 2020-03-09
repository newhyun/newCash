package com.example.newcash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private LinearLayout gameNav, shoppingNav, moreNav;
    private RelativeLayout cashBox;

    private TextView game1start;


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
}

