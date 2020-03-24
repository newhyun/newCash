package com.example.newcash;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class CouponActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    //adMob
    private AdView adView;
    //adMob

    private LinearLayout page_back;
    private Switch use_switch;
    private TextView use_ok, use_end;

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


        fragmentManager.beginTransaction().replace(R.id.coupon_container, new CouponUesFragment()).commit();

        use_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    fragmentManager.beginTransaction().replace(R.id.coupon_container, new CouponEndFragment()).commit();
                    use_end.setTextColor(Color.parseColor("#000000"));
                    use_ok.setTextColor(Color.parseColor("#959595"));
                }else {
                    fragmentManager.beginTransaction().replace(R.id.coupon_container, new CouponUesFragment()).commit();
                    use_ok.setTextColor(Color.parseColor("#000000"));
                    use_end.setTextColor(Color.parseColor("#959595"));
                }
            }
        });
    }

}
