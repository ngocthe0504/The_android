package pk_item_checkout;

import static com.example.test.MainActivity.http;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Checkout_Apdater extends BaseAdapter {
    private Context mContext;
    private ArrayList<Item_checkout> arrayList;
    int layout;

    public Checkout_Apdater(Context mContext, ArrayList<Item_checkout> arrayList, int layout) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        TextView txtTittle,txtgia,txtsoluong;
        ImageView img;
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        txtTittle= view.findViewById(R.id.txtTittle);
        txtgia =view.findViewById(R.id.txtgia);
        txtsoluong = view.findViewById(R.id.txtsoluong);
        img = view.findViewById(R.id.ivDayDetails);


        txtTittle.setText(arrayList.get(i).getTittle());
        txtgia.setText(decimalFormat.format(arrayList.get(i).getGia()) + " VNĐ" );
        txtsoluong.setText("Số lượng: "+arrayList.get(i).getSoluong());
        Glide.with(mContext).load(arrayList.get(i).getImage()).into(img);



        return view;
    }
}
