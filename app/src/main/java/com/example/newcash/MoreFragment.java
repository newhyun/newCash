package com.example.newcash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.securepreferences.SecurePreferences;

public class MoreFragment extends Fragment {

    public MoreFragment() {}

    private LinearLayout coupon_page, cash_page;
    private TextView more_my_code;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String recommend;
    private ClipboardManager clipboard;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        sharedPref = new SecurePreferences(getActivity(), "fncm0417", "fncm0417");
        editor = sharedPref.edit();
        recommend = sharedPref.getString("recommend", "");

        clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);



        coupon_page = view.findViewById(R.id.coupon_page);
        cash_page = view.findViewById(R.id.cash_page);

        more_my_code = view.findViewById(R.id.more_my_code);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.coupon_page:

                        Intent intent = new Intent(getActivity(), CouponActivity.class);
                        startActivity(intent);

                        break;

                    case R.id.cash_page:

                        Intent intent1 = new Intent(getActivity(), CashListActivity.class);
                        startActivity(intent1);

                        break;

                    case R.id.more_my_code:
                        Toast.makeText(getActivity(), "복사완료", Toast.LENGTH_SHORT).show();

                        ClipData clip = ClipData.newPlainText("내 추천코드", recommend);
                        clipboard.setPrimaryClip(clip);
                        break;

                }
            }
        };
        coupon_page.setOnClickListener(onClickListener);
        cash_page.setOnClickListener(onClickListener);
        more_my_code.setOnClickListener(onClickListener);

        more_my_code.setText(recommend);

        return view;
    }
}