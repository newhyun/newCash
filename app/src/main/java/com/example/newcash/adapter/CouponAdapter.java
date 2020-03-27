package com.example.newcash.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newcash.R;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.ViewHolder> {

private ArrayList<CouponDTO> arrayList;
private String jibun = "";

public class ViewHolder extends RecyclerView.ViewHolder {

    protected TextView cafe_name, menu_name, day_date;
    protected ImageView item_img;
    protected RelativeLayout use_end;

    public ViewHolder(View view) {

        super(view);
        this.cafe_name = view.findViewById(R.id.cafe_name);
        this.menu_name = view.findViewById(R.id.menu_name);
        this.day_date = view.findViewById(R.id.day_date);
        this.item_img = view.findViewById(R.id.item_img);
        this.use_end = view.findViewById(R.id.use_end);

    }
}
    public CouponAdapter(ArrayList<CouponDTO> list) {

        this.arrayList = list;
    }

    public CouponDTO getData(int position){

        return arrayList.get(position);
    }

    @Override
    public CouponAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_coupon_list, viewGroup, false);

        CouponAdapter.ViewHolder viewHolder = new CouponAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CouponAdapter.ViewHolder viewholder, int position) {

        viewholder.cafe_name.setText(arrayList.get(position).getCafe_name());
        viewholder.menu_name.setText(arrayList.get(position).getMenu_name());
        viewholder.day_date.setText(arrayList.get(position).getDay_date());
    }

    @Override
    public int getItemCount() {

        return (null != arrayList ? arrayList.size() : 0);
    }

    public void notifyDataSetChanged (String jibun) {

        this.jibun = jibun;
        super.notifyDataSetChanged();
    }

}