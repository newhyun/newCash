package com.example.newcash;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.securepreferences.SecurePreferences;

public class SaveActivity extends AppCompatActivity {

    private TextView save_ok, save_count, my_codenumber;
    private ImageView my_codeimg;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String recommend;
    private ClipboardManager clipboard;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        sharedPref = new SecurePreferences(SaveActivity.this, "fncm0417", "fncm0417");
        editor = sharedPref.edit();
        recommend = sharedPref.getString("recommend", "");

        clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

        Intent intent = getIntent();
        int shake_count = intent.getExtras().getInt("shake_save");

        save_ok = findViewById(R.id.save_ok);
        save_count = findViewById(R.id.save_count);
        my_codenumber = findViewById(R.id.my_codenumber);
        my_codeimg = findViewById(R.id.my_codeimg);

        save_count.setText(shake_count + "");


        save_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity 종료
                finish();

            }
        });


        my_codenumber.setText(recommend);
        //터치시 기본 팝업
        my_codeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SaveActivity.this, "복사완료", Toast.LENGTH_SHORT).show();

                ClipData clip = ClipData.newPlainText("내 추천코드", recommend);
                clipboard.setPrimaryClip(clip);
            }
        });

    }


}

