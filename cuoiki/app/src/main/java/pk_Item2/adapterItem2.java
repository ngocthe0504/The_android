package pk_Item2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.CategoryActivity;
import com.example.test.R;
import com.example.test.pk_Item.Item;

import java.util.ArrayList;

public class adapterItem2 extends RecyclerView.Adapter<adapterItem2.ItemViewHold2>  {

    ArrayList<Item2> itemLaocationsC;
    private Context mContext;
    final private ListItemClickListener mOnClickListener;

    public adapterItem2(Context context, ArrayList<Item2> itemLaocations, ListItemClickListener listener) {
        this.itemLaocationsC = itemLaocations;
        mOnClickListener = listener;
        this.mContext=context;
    }

    @NonNull

    @Override
    public ItemViewHold2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemrecyclecard2, parent, false);
        return new ItemViewHold2(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHold2 holder, int position) {


        final Item2 itemhelper2 = itemLaocationsC.get(position);
        holder.image.setImageResource(itemhelper2.getImage2());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void onClickGoToDetail(Item itemhelper){
        Intent intent = new Intent(mContext, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object",itemhelper);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return itemLaocationsC.size();

    }

    public interface ListItemClickListener {
        void onitemListClick(int clickedItemIndex);
    }

    public class ItemViewHold2 extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView layoutItem;
        ImageView image;



        public ItemViewHold2(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks
            layoutItem = itemView.findViewById(R.id.item_Cardview2);
            image = itemView.findViewById(R.id.item_image2);



        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onitemListClick(clickedPosition);
        }
    }

}
