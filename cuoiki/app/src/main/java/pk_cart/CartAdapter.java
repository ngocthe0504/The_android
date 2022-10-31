package pk_cart;

import static com.example.test.CartActivity.EventUntil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test.MainActivity;
import com.example.test.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartArrayList.get(i);
    }

    public class ViewHolder{
        public TextView txtTengiohang;
        public  TextView txtGiagiohang;
        public TextView txtSoluong;
        public ImageView imggiohang;
        public ImageButton btnplus,btnminus;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.itemcart,null);
            viewHolder.txtTengiohang = view.findViewById(R.id.cart_name);
            viewHolder.txtGiagiohang = view.findViewById(R.id.cart_price);
            viewHolder.imggiohang = view.findViewById(R.id.img_cart);
            viewHolder.txtSoluong = view.findViewById(R.id.txtquanlity);
            viewHolder.btnplus = view.findViewById(R.id.btnplus);
            viewHolder.btnminus = view.findViewById(R.id.btnminus);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Cart cart = (Cart) getItem(i);
        viewHolder.txtTengiohang.setText(cart.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiagiohang.setText(decimalFormat.format(cart.getGiaSP()) + " VNĐ");
        viewHolder.txtSoluong.setText(cart.getSoLuong()+"");
        Glide.with(context).load(cart.getHinhAnh()).into(viewHolder.imggiohang);


        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.txtSoluong.getText().toString())+1;
                int slht = MainActivity.cartArrayList.get(i).getSoLuong();
                long giaht = MainActivity.cartArrayList.get(i).getGiaSP();
                MainActivity.cartArrayList.get(i).setSoLuong(slmoinhat);
                long giamoi = (giaht * slmoinhat) / slht;
                MainActivity.cartArrayList.get(i).setGiaSP(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiagiohang.setText(decimalFormat.format(giamoi) + " VNĐ");

                EventUntil();
                finalViewHolder.txtSoluong.setText(String.valueOf(slmoinhat));
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.txtSoluong.getText().toString())-1;
                int slht = MainActivity.cartArrayList.get(i).getSoLuong();
                long giaht = MainActivity.cartArrayList.get(i).getGiaSP();
                MainActivity.cartArrayList.get(i).setSoLuong(slmoinhat);
                long giamoi = (giaht * slmoinhat) / slht;
                MainActivity.cartArrayList.get(i).setGiaSP(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiagiohang.setText(decimalFormat.format(giamoi) + " VNĐ");

                EventUntil();
                if(slmoinhat < 1)
                    MainActivity.cartArrayList.remove(i);


                else
                    finalViewHolder.txtSoluong.setText(String.valueOf(slmoinhat));

            }
        });

        return view;
    }


}
