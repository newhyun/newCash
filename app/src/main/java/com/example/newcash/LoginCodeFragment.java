package com.example.newcash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.securepreferences.SecurePreferences;

import java.util.Random;

public class LoginCodeFragment extends Fragment {

    public LoginCodeFragment() {}

    private TextView startBtn;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private String recommend;
    private ClipboardManager clipboard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_code, container, false);

        sharedPref = new SecurePreferences(getActivity(), "fncm0417", "fncm0417");
        editor = sharedPref.edit();
        recommend = sharedPref.getString("recommend", "");

         clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);


        LinearLayout login_top = ((LoginActivity)getActivity()).findViewById(R.id.login_top);
        TextView my_code = ((LoginActivity)getActivity()).findViewById(R.id.my_code);
        final TextView my_codenumber = ((LoginActivity)getActivity()).findViewById(R.id.my_codenumber);
        ImageView my_codeimg = ((LoginActivity)getActivity()).findViewById(R.id.my_codeimg);

        startBtn = view.findViewById(R.id.startBtn);
        TextView laterBtn = view.findViewById(R.id.laterBtn);


        EditText codeText = view.findViewById(R.id.codeText);

        login_top.setBackgroundColor(Color.parseColor("#ffffff"));

        container.setBackground(getResources().getDrawable(R.drawable.round_login_fffff));

        my_code.setVisibility(View.VISIBLE);
        my_codenumber.setVisibility(View.VISIBLE);
        my_codeimg.setVisibility(View.VISIBLE);

        codeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() == 6) {

                    startBtn.setEnabled(true);
                }
            }
        });


        if (recommend.equals("")) {

            //랜덤 스트링 생성
            StringBuffer temp = new StringBuffer();
            Random rnd = new Random();
            for (int i = 0; i < 6; i++) {
                int rIndex = rnd.nextInt(2);
                switch (rIndex) {
                    case 0:
                        // A-Z
                        temp.append((char) ((int) (rnd.nextInt(26)) + 65));

                        break;
                    case 1:
                        // 0-9
                        temp.append((rnd.nextInt(10)));
                        break;
                }
            }
            my_codenumber.setText(temp);

            editor.putString("recommend", temp + "");
            editor.apply();

        } else {

            my_codenumber.setText(recommend);
        }



        //터치시 기본 팝업
        my_codeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "복사완료", Toast.LENGTH_SHORT).show();

                ClipData clip = ClipData.newPlainText("내 추천코드", recommend);
                clipboard.setPrimaryClip(clip);
            }
        });



        //나중에 입력 버튼
        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });



//        RelativeLayout google = view.findViewById(R.id.google);
//
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                switch (v.getId()) {
//
//                    case R.id.google:
//
//                        Toast.makeText(getActivity(), "google 로그인", Toast.LENGTH_LONG).show();
//                        break;
//                }
//            }
//        };
//        google.setOnClickListener(onClickListener);

        return view;
    }
}
