package com.example.foodapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp.R;
import com.example.foodapp.model.Food;

import java.util.List;

public class FoodAdapter extends BaseAdapter {
    Activity activity;
    List<Food> dataList;
    public FoodAdapter(Activity activity, List<Food> dataList) {
        this.activity = activity;
        this.dataList = dataList;
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_food, null);

        ImageView banhkep = view.findViewById(R.id.banhkep);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvContent = view.findViewById(R.id.tvContent);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        Food food = dataList.get(i);
        banhkep.setImageResource(food.getId());
        tvTitle.setText(food.getTitle());
        tvContent.setText(food.getContent());
        tvPrice.setText(String.valueOf(food.getPrice()));
        return view;
    }
}
