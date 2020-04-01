package com.example.newcash;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;


public class LoginSelectFragment extends Fragment {

    public LoginSelectFragment() {}

    private static final int RC_SIGN_IN = 1000;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    private RelativeLayout google, facebook, kakao;
    private RelativeLayout Google_Login;

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


//        Google_Login = view.findViewById(R.id.google);


        return view;
    }

}
