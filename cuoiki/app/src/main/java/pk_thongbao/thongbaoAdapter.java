package pk_thongbao;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.test.R;

import java.util.List;

import pk_cart.CartAdapter;

public class thongbaoAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<thongbao> thongbaoList;

    public thongbaoAdapter(Context context, int layout, List<thongbao> thongbaoList) {
        this.context = context;
        this.layout = layout;
        this.thongbaoList = thongbaoList;
    }

    @Override
    public int getCount() {
        return thongbaoList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView txtTen =  (TextView) view.findViewById(R.id.textviewTen);
        TextView txtMoTa = (TextView) view.findViewById(R.id.textviewMoTa);
        ImageView imgHinh = (ImageView) view.findViewById(R.id.imageviewHinh);
        TextView txtTime = view.findViewById(R.id.textviewTime);

        thongbao thongbao = thongbaoList.get(i);
        txtTen.setText(thongbao.getTen());
        txtMoTa.setText(thongbao.getMoTa());
        txtTime.setText(thongbao.getTime());

        Glide.with(context).load(thongbao.getHinh()).into(imgHinh);

        return view;
    }
}
