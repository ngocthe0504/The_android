package com.example.test.pk_HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.ProductDetailActivity;
import com.example.test.R;

import java.util.ArrayList;

public class adapterphone extends RecyclerView.Adapter<adapterphone.PhoneViewHold>  {

    ArrayList<phonehelper> phoneLaocations;
    final private ListItemClickListener mOnClickListener;
    private Context mContext;
    public adapterphone(Context context,ArrayList<phonehelper> phoneLaocations, ListItemClickListener listener) {
        this.phoneLaocations = phoneLaocations;
        mOnClickListener = listener;
        this.mContext=context;
    }

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonerecyclercard, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {


        final phonehelper phonehelper = phoneLaocations.get(position);
        //holder.image.setImageResource(phonehelper.getImage());
        Glide.with(mContext).load(phonehelper.getImage()).into(holder.image);
        holder.star.setImageResource(phonehelper.getStar());
        holder.star2.setImageResource(phonehelper.getStar2());
        holder.star3.setImageResource(phonehelper.getStar3());
        holder.star4.setImageResource(phonehelper.getStar4());
        holder.title.setText(phonehelper.getTitle());
        holder.price.setText(phonehelper.getPrice());
        holder.note.setText(phonehelper.getNote());
        holder.rate.setText(phonehelper.getRate());
        //xu li su kien click
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    onClickGoToDetail(phonehelper);
            }
        });

    }
    // xu li su kien click vao product
    private void onClickGoToDetail(phonehelper phonehelper){
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",phonehelper);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return phoneLaocations.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView layoutItem;
        ImageView image,star,star2,star3,star4;
        TextView title,note,rate,price;

        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks
            layoutItem = itemView.findViewById(R.id.phone_Cardview);
            image = itemView.findViewById(R.id.phone_image);
            title = itemView.findViewById(R.id.phone_title);
            star = itemView.findViewById(R.id.phone_start);
            star2 = itemView.findViewById(R.id.phone_start2);
            star3 = itemView.findViewById(R.id.phone_start3);
            star4 = itemView.findViewById(R.id.phone_start4);
            rate  = itemView.findViewById(R.id.phone_rate);
            note = itemView.findViewById(R.id.phone_note);
            price = itemView.findViewById(R.id.phone_price);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onphoneListClick(clickedPosition);
        }
    }   

}
