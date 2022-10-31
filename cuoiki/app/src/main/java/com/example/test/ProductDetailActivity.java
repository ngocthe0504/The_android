package com.example.test;


import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.pendingSMSCount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.test.pk_HelperClasses.adapterphone;
import com.example.test.pk_HelperClasses.phonehelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pk_HelperClassesS.adapterphoneS;
import pk_HelperClassesS.phonehelperS;
import pk_cart.Cart;

public class ProductDetailActivity extends AppCompatActivity implements adapterphone.ListItemClickListener{
    Toolbar toolbar;
    TextView smsCountTxt;
    RecyclerView phoneRecycler;
    RecyclerView.Adapter adapter;
    TextView tvTen,tvGia,tvsize,tvloai,tvram,tvrom,tvpin,soluong;
    Button addCart ;
    ImageView imageView;
    ImageButton minus,plus;
    ArrayList<phonehelper> Phones = new ArrayList<phonehelper>();
    private static  final String BASE_URL_SP = "http://192.168.1.62/androidwebservice/sanpham.php";
    private String URL = http+"androidwebservice/getSPbyDM.php";
    int idSP=0;
    String Tittle = "";
    int price=0;
    String Image = "";
    String idDM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if(MainActivity.cartArrayList != null)
        {
            pendingSMSCount = MainActivity.cartArrayList.size();
        }
        anhxa();
        setSupportActionBar(toolbar);
        getInfomation();

        getSPbyDM();
        EvenButton();
        SetQty();
    }

    private void SetQty() {
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(soluong.getText().toString());
                if(sl > 1 )
                    soluong.setText(""+ (sl-1));
                else
                    soluong.setText("1");
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sl = Integer.parseInt(soluong.getText().toString());

                if(sl < 9)
                    soluong.setText(""+ (sl+1));
                else
                    soluong.setText("9");

            }
        });
    }

    private void EvenButton() {
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.cartArrayList.size() > 0 ){
                    int solg = Integer.parseInt(soluong.getText().toString());
                    boolean exits = false ;
                    for(int i = 0 ; i < MainActivity.cartArrayList.size(); i++){
                        if(MainActivity.cartArrayList.get(i).getIdSP() == idSP){
                            MainActivity.cartArrayList.get(i).setSoLuong(MainActivity.cartArrayList.get(i).getSoLuong() + solg);
                            MainActivity.cartArrayList.get(i).setGiaSP(price * MainActivity.cartArrayList.get(i).getSoLuong());
                            exits = true;

                        }
                    }
                    if(exits == false){
                        int sl = Integer.parseInt(soluong.getText().toString());
                        long Giamoi = sl * price ;
                        MainActivity.cartArrayList.add(new Cart(idSP,Tittle,Giamoi,Image,sl));

                    }
                }else{
                    int sl = Integer.parseInt(soluong.getText().toString());
                    long Giamoi = sl * price ;
                    MainActivity.cartArrayList.add(new Cart(idSP,Tittle,Giamoi,Image,sl));
                    pendingSMSCount = MainActivity.cartArrayList.size();

                }

                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getSanPham(){
        NumberFormat formatter = new DecimalFormat("###,###,###");
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL_SP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0 ; i <=response.length();i++){
                            try{
                                JSONObject object = response.getJSONObject(i);
                                phonehelper phone = new phonehelper();
                                phone.setIdDM(object.getInt("idDM"));
                                phone.setIdSP(object.getInt("idSP"));
                                phone.setTitle(object.getString("Tittle"));
                                phone.setImage(object.getString("HinhAnh"));
                                phone.setNote(object.getString("UuDai"));
                                phone.setPrice(formatter.format(object.getInt("Gia")));
                                phone.setRate(object.getInt("SoDanhGia")+" đánh giá");
                                phone.setSizemanhinh(object.getString("size"));
                                phone.setLoaimanhinh(object.getString("loai"));
                                phone.setRam(object.getString("ram"));
                                phone.setRom(object.getString("rom"));
                                phone.setPin(object.getString("pin"));
                                phone.setStar(R.drawable.star);
                                phone.setStar2(R.drawable.star);
                                phone.setStar3(R.drawable.star);
                                phone.setStar4(R.drawable.star);
                                Phones.add(phone);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        phoneRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphone(getApplicationContext(),Phones,ProductDetailActivity.this);
                        phoneRecycler.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText( null, "lỗi", Toast.LENGTH_SHORT).show();
                Log.d("tag","onErrorRespone: " +error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }
    private void getInfomation(){

        String size = "";
        String loai = "";
        String ram = "";
        String rom = "";
        String pin="";
        if((phonehelper) getIntent().getSerializableExtra("object") != null) {
            phonehelper phone = (phonehelper) getIntent().getSerializableExtra("object");
            idDM = String.valueOf(phone.getIdDM());
            idSP = phone.getIdSP();
            Tittle = phone.getTitle();
            Image = phone.getImage();
            price = phone.getGiaInt();
            size = phone.getSizemanhinh();
            loai = phone.getLoaimanhinh();
            ram = phone.getRam();
            rom = phone.getRom();
            pin = phone.getPin();

            tvTen.setText(Tittle);
            DecimalFormat format = new DecimalFormat("###,###,###");
            tvGia.setText(format.format(price) + " VNĐ");
            Glide.with(getApplicationContext()).load(Image).into(imageView);
            tvsize.setText(size);
            tvloai.setText(loai);
            tvram.setText(ram);
            tvrom.setText(rom);
            tvpin.setText(pin);
        }else{
            phonehelperS phone = (phonehelperS) getIntent().getSerializableExtra("objectS");
            idDM = String.valueOf(phone.getIdDM());
            idSP = phone.getIdSP();
            Tittle = phone.getTitle();
            Image = phone.getImage();
            price = phone.getGiaInt();
            size = phone.getSizemanhinh();
            loai = phone.getLoaimanhinh();
            ram = phone.getRam();
            rom = phone.getRom();
            pin = phone.getPin();

            tvTen.setText(Tittle);
            DecimalFormat format = new DecimalFormat("###,###,###");
            tvGia.setText(format.format(price) + " VNĐ");
            Glide.with(getApplicationContext()).load(Image).into(imageView);
            tvsize.setText(size);
            tvloai.setText(loai);
            tvram.setText(ram);
            tvrom.setText(rom);
            tvpin.setText(pin);
        }
    }


    private void anhxa(){
        tvTen = findViewById(R.id.tvtensp);
        tvGia = findViewById(R.id.tvgia);
        imageView = findViewById(R.id.imageView);
        toolbar = findViewById(R.id.toolbarDetail);
        phoneRecycler = findViewById(R.id.my_recycler5);
        tvsize = findViewById(R.id.sizemanhinh);
        tvloai = findViewById(R.id.loaimanhinh);
        tvram = findViewById(R.id.ram);
        tvrom = findViewById(R.id.rom);
        tvpin = findViewById(R.id.pin);
        addCart = findViewById(R.id.add_cart);
        soluong = findViewById(R.id.btslg);
       plus    = findViewById(R.id.cong);
        minus = findViewById(R.id.tru);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        MenuItem menuItem = menu.findItem(R.id.actioncart);
        smsCountTxt = findViewById(R.id.cart_badge);
        if(pendingSMSCount==0){
            menuItem.setActionView(null);
        }else{
            menuItem.setActionView(R.layout.custom_cart_layout);
            View view = menuItem.getActionView();
            smsCountTxt = view.findViewById(R.id.cart_badge);
            smsCountTxt.setText(String.valueOf((pendingSMSCount)));
            setupBadge();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onOptionsItemSelected(menuItem);
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  R.id.actioncart:
                    Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                    startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }
    private void setupBadge() {
        if (smsCountTxt != null) {
            if (pendingSMSCount == 0) {
                if (smsCountTxt.getVisibility() != View.GONE) {
                    smsCountTxt.setVisibility(View.GONE);
                }
            } else {
                smsCountTxt.setText(String.valueOf(Math.min(pendingSMSCount, 99)));
                if (smsCountTxt.getVisibility() != View.VISIBLE) {
                    smsCountTxt.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

    }

    public void getSPbyDM() {

        NumberFormat formatter = new DecimalFormat("###,###,###");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Phones.clear();

                try{
                    JSONObject jsonObject  = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if(success.equals("1")){

                        for(int i = 0 ; i < jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            phonehelper phone = new phonehelper();
                            phone.setIdDM(object.getInt("idDM"));
                            phone.setIdSP(object.getInt("idSP"));
                            phone.setTitle(object.getString("Tittle"));
                            phone.setImage(http+ object.getString("HinhAnh"));
                            phone.setNote(object.getString("UuDai"));
                            phone.setPrice(formatter.format(object.getInt("Gia"))+" VNĐ");
                            phone.setGiaInt(object.getInt("Gia"));
                            phone.setRate(object.getInt("SoDanhGia")+" đánh giá");
                            phone.setSizemanhinh(object.getString("size"));
                            phone.setLoaimanhinh(object.getString("loai"));
                            phone.setRam(object.getString("ram"));
                            phone.setRom(object.getString("rom"));
                            phone.setPin(object.getString("pin"));
                            phone.setStar(R.drawable.star);
                            phone.setStar2(R.drawable.star);
                            phone.setStar3(R.drawable.star);
                            phone.setStar4(R.drawable.star);
                            Phones.add(phone);
                        }
                        phoneRecycler.setLayoutManager(new GridLayoutManager(getApplicationContext(),1,GridLayoutManager.HORIZONTAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphone(getApplicationContext(),Phones,ProductDetailActivity.this);
                        phoneRecycler.setAdapter(adapter);
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),"Error: " + e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error: " + error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("idDM", idDM);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}