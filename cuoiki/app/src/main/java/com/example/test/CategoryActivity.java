package com.example.test;

import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.islogin;
import static com.example.test.MainActivity.pendingSMSCount;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.pk_HelperClasses.adapterphone;
import com.example.test.pk_HelperClasses.phonehelper;
import com.example.test.pk_Item.Item;
import com.example.test.pk_img_viewflper.Photo;
import com.example.test.pk_img_viewflper.PhotoAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;
import pk_HelperClassesS.adapterphoneS;
import pk_HelperClassesS.phonehelperS;
import pk_Item2.Item2;
import pk_Item2.adapterItem2;
import pk_price.AdapterPrice;
import pk_price.Price;

public class CategoryActivity extends AppCompatActivity implements adapterItem2.ListItemClickListener, AdapterPrice.ListItemClickListener, adapterphoneS.ListItemClickListener, adapterphone.ListItemClickListener {
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    RecyclerView itemRecycler5;
    RecyclerView itemRecycler6;
    private List<Photo> mlistphoto;
    private ViewPager viewPager;
    CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    RecyclerView.Adapter adapter;
    RecyclerView phoneRecyclerS;
    String idDM;
    Item item;
    TextView smsCountTxt;
    TextView txttenDM;
    ArrayList<Price> itemlocationsC = new ArrayList<>();
    RadioButton rdXemNhieu,rdGiathap,rdGiaCao,rdAll;
    private static  final String BASE_URL_SP = http+"androidwebservice/getallSP.php";
    private static  final String URL_Sort_Price = http+"androidwebservice/SortByPrice.php";
    private static  final String URL_KeyWord= http+"androidwebservice/getSpByKeyWord.php";
    private String URL = http+"androidwebservice/getSPbyDM.php";
    ArrayList<phonehelperS> Phones = new ArrayList<>();
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Anhxa();


        bottomNavigationView.setSelectedItemId(R.id.item);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        if(islogin!=false){
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(0,0);
                        }else{
                            //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            //overridePendingTransition(0,0);
                            DialogSubmit();
                        }
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.item:
                        return true;
                    case R.id.action_notification:
                        if(islogin!=false){
                            startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                            overridePendingTransition(0,0);
                        }else{
                            //startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            //overridePendingTransition(0,0);
                            DialogSubmit();
                        }
                        return true;
                }
                return false;
            }
        });


        setSupportActionBar(toolbar);


        mlistphoto = getListPhoto();


        photoAdapter = new PhotoAdapter(this,mlistphoto );
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        itemRecycler5();
        itemRecycler6 = findViewById(R.id.recyclerPrice);
        itemRecycler6();

        radiobutton();



        if(getIntent().getSerializableExtra("data_price") !=null){
            sortbyPrice();
        } else if(key!=null){
            key = getIntent().getExtras().getString("key");
            getSpByKeyWord(key);
        }else{
            getSPbyDM();
        }




    }
    private void getSpByKeyWord(String key){
        NumberFormat formatter = new DecimalFormat("###,###,###");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KeyWord, new Response.Listener<String>() {
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

                            phonehelperS phone = new phonehelperS();
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
                        phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                        phoneRecyclerS.setAdapter(adapter);
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
                data.put("key", key);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void sortbyPrice(){
            Price price = (Price) getIntent().getSerializableExtra("data_price");
            sortPrice(price.getMin(),price.getMax());

    }

    private void sortPrice(int min,int max){
        NumberFormat formatter = new DecimalFormat("###,###,###");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Sort_Price, new Response.Listener<String>() {
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

                            phonehelperS phone = new phonehelperS();
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
                        phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                        phoneRecyclerS.setAdapter(adapter);
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
                data.put("min", String.valueOf(min));
                data.put("max", String.valueOf(max));
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void radiobutton() {
        rdGiaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(Phones, new Comparator<phonehelperS>() {

                    @Override
                    public int compare(phonehelperS phonehelperS, phonehelperS t1) {
                        if (phonehelperS.getGiaInt() > t1.getGiaInt()) {
                            return -1;
                        } else {
                            if (phonehelperS.getGiaInt() == t1.getGiaInt()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                });
                phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                phoneRecyclerS.setAdapter(adapter);
            }

        });
        rdGiathap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(Phones, new Comparator<phonehelperS>() {

                    @Override
                    public int compare(phonehelperS phonehelperS, phonehelperS t1) {
                        if (phonehelperS.getGiaInt() < t1.getGiaInt()) {
                            return -1;
                        } else {
                            if (phonehelperS.getGiaInt() == t1.getGiaInt()) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                });
                phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                phoneRecyclerS.setAdapter(adapter);
            }
        });
        rdXemNhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSPbyDM();
            }
        });
        rdAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSanPham();

            }
        });

    }

    private void DialogSubmit(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        //alert.setIcon(R.);
        alert.setMessage("Bạn cần đăng nhập để xem thông báo");

        alert.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
        alert.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }


    private void Anhxa() {
        if(MainActivity.cartArrayList != null)
        {
            pendingSMSCount = MainActivity.cartArrayList.size();
        }
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        toolbar = findViewById(R.id.toolbarCategory);
        viewPager = findViewById(R.id.viewpagerC);
        itemRecycler5 = findViewById(R.id.my_recyclerItem);
        phoneRecyclerS = findViewById(R.id.recyclerSmallPhone);
        circleIndicator = findViewById(R.id.circle_indicatorC);
        rdGiaCao = findViewById(R.id.rd_giacao);
        rdGiathap = findViewById(R.id.rd_giathap);
        rdXemNhieu = findViewById(R.id.rd_Xemnhieu);
        rdAll = findViewById(R.id.rd_All);
        txttenDM = findViewById(R.id.txttenDM);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if(data != null){
            key = data.getString("key");
        }else{
            key = null;
        }
        if(getIntent().getSerializableExtra("object") !=null){
            item = (Item) getIntent().getSerializableExtra("object");
            idDM =  String.valueOf(item.getId());
            txttenDM.setText(""+item.getTitle());
        }else if(key !=null){

            txttenDM.setText("Hiển  thị kết quả: "+ key + "");
        }else{
            idDM = "";
        }

    }


    private void getSanPham(){
        NumberFormat formatter = new DecimalFormat("###,###,###");
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL_SP, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Phones.clear();
                        for(int i = 0 ; i <=response.length();i++){
                            try{
                                JSONObject object = response.getJSONObject(i);
                                phonehelperS phone = new phonehelperS();
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
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                        phoneRecyclerS.setAdapter(adapter);
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

    private void itemRecycler6() {
        //All Gradients
        itemRecycler6.setHasFixedSize(true);
        //phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        itemRecycler6.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false));


        itemlocationsC.add(new Price( "Dưới 2 triệu",0,2000000));
        itemlocationsC.add(new Price( "Từ 2 - 4 triệu",2000000,4000000));
        itemlocationsC.add(new Price( "Từ 4 -7 triệu",4000000,7000000));
        itemlocationsC.add(new Price( "Từ 7 - 13 triệu",7000000,13000000));
        itemlocationsC.add(new Price( "Trên 15 triệu",15000000,1000000000));




        adapter = new AdapterPrice(this,itemlocationsC,this);
        itemRecycler6.setAdapter(adapter);

    }


    private void itemRecycler5() {
        //All Gradients
        itemRecycler5.setHasFixedSize(true);
        //phoneRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        itemRecycler5.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.HORIZONTAL,false));

        ArrayList<Item2> itemlocationsC = new ArrayList<>();
        itemlocationsC.add(new Item2( R.drawable.iphonelogo2));
        itemlocationsC.add(new Item2( R.drawable.oppo_logo));
        itemlocationsC.add(new Item2( R.drawable.onepluslogo2));
        itemlocationsC.add(new Item2( R.drawable.sslogo2));
        itemlocationsC.add(new Item2( R.drawable.vivologo2));
        itemlocationsC.add(new Item2( R.drawable.realme_logo));
        itemlocationsC.add(new Item2( R.drawable.apple));
        itemlocationsC.add(new Item2( R.drawable.apple));
        itemlocationsC.add(new Item2( R.drawable.apple));
        itemlocationsC.add(new Item2( R.drawable.apple));



        adapter = new adapterItem2(this,itemlocationsC,this);
        itemRecycler5.setAdapter(adapter);


    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.view1));
        list.add(new Photo(R.drawable.view2));
        list.add(new Photo(R.drawable.view3));
        list.add(new Photo(R.drawable.view1));
        list.add(new Photo(R.drawable.view2));
        list.add(new Photo(R.drawable.view3));
        return list;
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

                    Intent intent = new Intent(CategoryActivity.this, CartActivity.class);
                    startActivity(intent);

                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onitemListClick(int clickedItemIndex) {

    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

                            phonehelperS phone = new phonehelperS();
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
                        phoneRecyclerS.setLayoutManager(new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false));
                        //itemRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                        adapter = new adapterphoneS(getApplicationContext(),Phones,CategoryActivity.this);
                        phoneRecyclerS.setAdapter(adapter);
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