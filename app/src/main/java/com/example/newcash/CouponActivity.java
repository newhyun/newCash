package com.example.newcash;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newcash.adapter.CouponAdapter;
import com.example.newcash.adapter.CouponDTO;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CouponActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    //adMob
    private AdView adView;
    //adMob

    private LinearLayout page_back, coupon_item_list;
    private Switch use_switch;
    private TextView use_ok, use_end;

    private RecyclerView coupon_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        fragmentManager = getSupportFragmentManager();

        //adMob start
        MobileAds.initialize(this, "ca-app-pub-5646098133984483/4286200269");
        //adMob BANNER
        AdSize adSize = new AdSize(300, 50);

        adView = findViewById(R.id.adView);
        page_back = findViewById(R.id.page_back);

        use_switch = findViewById(R.id.use_switch);
        use_ok = findViewById(R.id.use_ok);
        use_end = findViewById(R.id.use_end);

        coupon_list = findViewById(R.id.coupon_list);
        coupon_item_list = findViewById(R.id.coupon_item_list);


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);
        //adMob end

        page_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //RecyclerView start
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CouponActivity.this);
        coupon_list.setLayoutManager(linearLayoutManager);

        ArrayList couponDTO = new ArrayList<CouponDTO>();

        // 바코드이미지, 시리얼번호, 상세설명 주소
        couponDTO.add(new CouponDTO("투썸", "아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("스타벅스", "아이스 아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("이디야", "아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("엔젤리너스", "아이스 아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("맘모스커피", "아이스 아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("더벤티", "아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("맘모스커피", "아이스 아메리카노", "2020.06.06", "String item_img", "1"));
        couponDTO.add(new CouponDTO("더벤티", "아메리카노", "2020.06.06", "String item_img", "1"));


        CouponAdapter couponAdapter = new CouponAdapter(couponDTO);
        coupon_list.setAdapter(couponAdapter);

        final GestureDetector gestureDetector = new GestureDetector(CouponActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(use_switch.getWindowToken(), 0);

                return true;
            }
        });
        //RecyclerView end


        use_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    use_end.setTextColor(Color.parseColor("#000000"));
                    use_ok.setTextColor(Color.parseColor("#959595"));
                } else {
                    use_ok.setTextColor(Color.parseColor("#000000"));
                    use_end.setTextColor(Color.parseColor("#959595"));
                }
            }
        });

    }


}
