package com.example.newcash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    // drawer layout
    private DrawerLayout drawerLayout;
    private View drawerView;
    private ImageView menubar, drawer_close;

    private LinearLayout gameNav, shoppingNav, moreNav;
    private RelativeLayout cashBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        // 사이드메뉴
        menubar = findViewById(R.id.menubar);
        drawer_close = findViewById(R.id.drawer_close);

        // drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerView = findViewById(R.id.drawer_page);
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

                    case R.id.menubar:

                        drawerLayout.openDrawer(drawerView);
                        break;

                    case R.id.drawer_close:

                        drawerLayout.closeDrawer(drawerView);
                        break;

                    case R.id.game_nav:

                        drawerLayout.closeDrawer(drawerView);

                        // Fragment 이동시 R.id.main_container == FrameLayout을 지정
                        // 이곳에 계속 프래그먼트를 올려서 빠르게 빠르게 바꿈
                        fragmentManager.beginTransaction().replace(R.id.main_container, new GameFragment()).commit();
                        cashBox.setVisibility(View.VISIBLE);
                        break;

                    case R.id.shopping_nav:

                        drawerLayout.closeDrawer(drawerView);

                        fragmentManager.beginTransaction().replace(R.id.main_container, new ShoppingFragment()).commit();
                        cashBox.setVisibility(View.VISIBLE);
                        break;

                    case R.id.more_nav:

                        drawerLayout.closeDrawer(drawerView);

                        fragmentManager.beginTransaction().replace(R.id.main_container, new MoreFragment()).commit();
                        cashBox.setVisibility(View.GONE);
                        break;
                }
            }


        };

        gameNav.setOnClickListener(onClickListener);
        shoppingNav.setOnClickListener(onClickListener);
        moreNav.setOnClickListener(onClickListener);
        menubar.setOnClickListener(onClickListener);
        drawer_close.setOnClickListener(onClickListener);
    }
}