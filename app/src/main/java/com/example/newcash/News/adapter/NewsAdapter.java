package com.example.newcash.News.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newcash.AdDialogFragment;
import com.example.newcash.News.mainA;
import com.example.newcash.News.mainB;
import com.example.newcash.News.mainC;

public class NewsAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public NewsAdapter(FragmentManager fragmentManager, int NumOfTabs) {
        super(fragmentManager);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {

            case 0:
                mainA tab1 = new mainA();
                return tab1;

            case 1:
                mainB tab2 = new mainB();
                return tab2;

            case 2:
                mainC tab3 = new mainC();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {

        return mNumOfTabs;
    }
}
