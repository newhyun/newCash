package com.example.newcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SaveActivity extends AppCompatActivity {

    private TextView save_ok, save_count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_save);

        Intent intent = getIntent();
        int shake_count = intent.getExtras().getInt("shake_save");

        save_ok = findViewById(R.id.save_ok);
        save_count = findViewById(R.id.save_count);

        save_count.setText(shake_count + "");

        //시청버튼
        save_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // activity 종료

                finish();

            }
        });


    }


}

