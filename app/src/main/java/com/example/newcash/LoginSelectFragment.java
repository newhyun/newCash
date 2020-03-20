package com.example.newcash;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;


public class LoginSelectFragment extends Fragment {

    public LoginSelectFragment() {}

    public LoginButton login_btn_kakaologin;
    private RelativeLayout google, facebook, kakao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_select, container, false);

        google = view.findViewById(R.id.google);
        facebook = view.findViewById(R.id.facebook);
        kakao =view.findViewById(R.id.kakao);

//        login_btn_kakaologin = view.findViewById(R.id.login_btn_kakaologin);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.google:

                        FragmentManager fragmentManager;
                        fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.anim_slide_in_right, 0);
                        fragmentTransaction.replace(R.id.login_container, new LoginCodeFragment());
                        fragmentTransaction.commit();

                        break;

                    case R.id.facebook:

                        break;

                    case R.id.kakao:

//                        login_btn_kakaologin.performClick();

                        break;
                }
            }
        };
        google.setOnClickListener(onClickListener);
        facebook.setOnClickListener(onClickListener);
        kakao.setOnClickListener(onClickListener);

        return view;
    }



}
