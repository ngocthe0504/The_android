package com.example.test;



import static com.example.test.LoginActivity.user;
import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.islogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
import pk_thongbao.thongbao;
import pk_thongbao.thongbaoAdapter;

public class NotificationActivity extends AppCompatActivity {
    ListView lvThongBao;
    ArrayList<thongbao> arraythongbao;
    thongbaoAdapter adapter;
    BottomNavigationView bottomNavigationView;
    private String URL = http+"androidwebservice/getTB.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.action_notification);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        if(islogin!=false){
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            overridePendingTransition(0,0);
                        }else{
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            overridePendingTransition(0,0);
                        }
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.item:
                        startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.action_notification:
                        return true;
                }

                return false;
            }
        });

//        AnhXa();
        getTB();
//        adapter = new thongbaoAdapter(this, R.layout.dong_thongbao, arraythongbao);
//        lvThongBao.setAdapter(adapter);
    }
    private void AnhXa() {
        lvThongBao = (ListView) findViewById(R.id.listviewThongBao);
        arraythongbao = new ArrayList<>();
       // arraythongbao.add(new thongbao(1,"Bạn đã mua hàng thành công", "Đơn hàng 32145 đã hoàn thành.", R.drawable.iphone1));


    }
    public void getTB() {
        lvThongBao = (ListView) findViewById(R.id.listviewThongBao);
        arraythongbao = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arraythongbao.clear();
                try{
                    JSONObject jsonObject  = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if(success.equals("1")){

                        for(int i = 0 ; i < jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);


                            thongbao thongbao = new thongbao();
                            thongbao.setTen (object.getString("Tittle"));
                            thongbao.setMoTa(object.getString("Chitiet"));
                            thongbao.setHinh(http+ object.getString("HinhAnh"));
                            thongbao.setTime(object.getString("time"));

                            arraythongbao.add(thongbao);
                        }
                        adapter = new thongbaoAdapter(getApplicationContext(), R.layout.dong_thongbao, arraythongbao);
                        lvThongBao.setAdapter(adapter);
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
                data.put("idUser", String.valueOf(user.getIdUser()));

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}