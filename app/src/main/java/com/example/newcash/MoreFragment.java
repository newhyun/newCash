package com.example.newcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MoreFragment extends Fragment {

    public MoreFragment() {}

    private LinearLayout coupon_page, mycode_page;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);

        coupon_page = view.findViewById(R.id.coupon_page);
        mycode_page = view.findViewById(R.id.mycode_page);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.coupon_page:

                        Intent intent = new Intent(getActivity(), CouponActivity.class);
                        startActivity(intent);

                        break;

                    case R.id.mycode_page:

                        break;

                }
            }
        };
        coupon_page.setOnClickListener(onClickListener);
        mycode_page.setOnClickListener(onClickListener);


        return view;
    }
}