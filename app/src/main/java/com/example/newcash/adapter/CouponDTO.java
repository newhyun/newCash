package com.example.newcash.adapter;

import android.media.Image;

public class CouponDTO {

    String cafe_name;
    String menu_name;
    String day_date;
    String item_img;
    String use_end;

    public CouponDTO(String cafe_name, String menu_name, String day_date, String item_img, String use_end) {
        this.cafe_name = cafe_name;
        this.menu_name = menu_name;
        this.day_date = day_date;
        this.item_img = item_img;
        this.use_end = use_end;
    }

    public String getCafe_name() {
        return cafe_name;
    }

    public void setCafe_name(String cafe_name) {
        this.cafe_name = cafe_name;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getDay_date() {
        return day_date;
    }

    public void setDay_date(String day_date) {
        this.day_date = day_date;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public String getUse_end() {
        return use_end;
    }

    public void setUse_end(String use_end) {
        this.use_end = use_end;
    }
}
