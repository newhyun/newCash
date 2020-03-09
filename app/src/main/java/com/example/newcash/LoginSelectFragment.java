package com.example.newcash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginSelectFragment extends Fragment {

    public LoginSelectFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_select, container, false);

        RelativeLayout google = view.findViewById(R.id.google);
        RelativeLayout facebook = view.findViewById(R.id.facebook);
        RelativeLayout kakao =view.findViewById(R.id.kakao);

//        google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getApplicationContext(), "구글로 로그인", Toast.LENGTH_LONG).show();
//            }
//        });

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
