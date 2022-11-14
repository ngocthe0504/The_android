package com.example.btkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.btkt.model.Singer;

import java.util.List;

public class SingerAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Singer> singerList;

    public SingerAdapter(Context context, int layout, List<Singer> singerList) {
        this.context = context;
        this.layout = layout;
        this.singerList = singerList;
    }

    @Override
    public int getCount() {
        return singerList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View covertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        covertView = inflater.inflate(layout, null);
        TextView tvName = (TextView) covertView.findViewById(R.id.tvName);
        TextView tvStageName = (TextView) covertView.findViewById(R.id.tvStageName);
        TextView tvCountry = (TextView) covertView.findViewById(R.id.tvQuocgia);
        TextView tvStar = (TextView) covertView.findViewById(R.id.tvStar);
        ImageView img = (ImageView) covertView.findViewById(R.id.img);
        tvName.setText(singerList.get(i).name);
        tvStageName.setText(singerList.get(i).StageName);
        tvCountry.setText(singerList.get(i).Country);
        tvStar.setText((int) singerList.get(i).star);
        img.setImageResource(singerList.get(i).img);
        return null;
    }
}
