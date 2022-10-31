package com.example.test.adapter_ordered;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import pk_lichsuhonhang.DaHuy;

import com.bumptech.glide.Glide;
import com.example.test.R;

import java.text.DecimalFormat;
import java.util.List;

public class DaHuy_adapter extends RecyclerView.Adapter<DaHuy_adapter.MyViewHolder>{

    List<DaHuy> list;
    Context context;

    public DaHuy_adapter(List<DaHuy> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dahuy, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        final DaHuy dahuy = list.get(position);
        holder.name.setText("" + dahuy.getTitle());

        holder.slg.setText("Số lượng: " + dahuy.getSoluong());
        holder.tongt.setText("Tổng tiền: " + decimalFormat.format(Integer.parseInt(dahuy.getTongTien())) + " VNĐ");
        Glide.with(context).load(dahuy.getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        Log.i("DebugY", "Running");

        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView name, slg, tongt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.ten);
            img = itemView.findViewById(R.id.img);
            slg = itemView.findViewById(R.id.slg);
            tongt = itemView.findViewById(R.id.tongtien);
        }
    }
}
