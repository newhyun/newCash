package com.example.newcash.News;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newcash.MainActivity;
import com.example.newcash.R;

public class mainA extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_a, container, false);


        TextView gamestart = view.findViewById(R.id.gamestart);
        TextView watchbtn = view.findViewById(R.id.watchbtn);
        TextView cancelbtn = view.findViewById(R.id.cancelbtn);




        return view;
    }


}