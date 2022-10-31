package com.example.test.pk_Item;

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
import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.CategoryActivity;

import java.util.ArrayList;

public class adapterItem extends RecyclerView.Adapter<adapterItem.ItemViewHold>  {

    ArrayList<Item> itemLaocations;
    private Context mContext;
    final private ListItemClickListener mOnClickListener;

    public adapterItem(Context context, ArrayList<Item> itemLaocations, MainActivity listener) {
        this.itemLaocations = itemLaocations;
        mOnClickListener = listener;
        this.mContext=context;
    }

    @NonNull

    @Override
    public ItemViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecyclercard, parent, false);
        return new ItemViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHold holder, int position) {


        final Item itemhelper = itemLaocations.get(position);
        Glide.with(mContext).load(itemhelper.getImage()).into(holder.image);
        //holder.title.setText(itemhelper.getTitle());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(itemhelper);
            }
        });
    }
    private void onClickGoToDetail(Item itemhelper){
        Intent intent = new Intent(mContext, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",itemhelper);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return itemLaocations.size();

    }

    public interface ListItemClickListener {
        void onitemListClick(int clickedItemIndex);
    }

    public class ItemViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView layoutItem;
        ImageView image;
        TextView title;


        public ItemViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks
            layoutItem = itemView.findViewById(R.id.item_Cardview);
            image = itemView.findViewById(R.id.item_image);
            //title = itemView.findViewById(R.id.item_title);


        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onitemListClick(clickedPosition);
        }
    }

}
