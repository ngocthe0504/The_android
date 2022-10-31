package com.example.test.fragment_ordered;

import static com.example.test.LoginActivity.user;
import static com.example.test.MainActivity.http;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import pk_lichsuhonhang.MyDividerItemDecoration;
import com.example.test.R;
import com.example.test.adapter_ordered.DangGiao_adapter;
import pk_lichsuhonhang.danggiao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DangGiao_Fragment extends Fragment {

    RecyclerView rcv;
    DangGiao_adapter adapter;
    List<danggiao> list = new ArrayList<>();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String URL = http+"androidwebservice/LichSuDH.php";
    private String mParam1;
    private String mParam2;

    public DangGiao_Fragment() {
        // Required empty public constructor
    }

    public static DangGiao_Fragment newInstance(String param1, String param2) {
        DangGiao_Fragment fragment = new DangGiao_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danggiao, container, false);
        rcv = view.findViewById(R.id.chiTiet_danggiao);
        getTB();
        return view;

    }
    public void getTB() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                list.clear();
                try{
                    JSONObject jsonObject  = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("login");
                    if(success.equals("1")){

                        for(int i = 0 ; i < jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            danggiao danggiao = new danggiao();
                            danggiao.setTitle(object.getString("Tittle"));
                            danggiao.setImage(http+object.getString("HinhAnh"));
                            danggiao.setSoluong(object.getString("SoLuong"));
                            int sl = Integer.parseInt(object.getString("SoLuong"));
                            danggiao.setTongtien(""+ (sl *  Integer.parseInt(object.getString("DonGia"))) );

                            list.add(danggiao);

                        }
                        LinearLayoutManager llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);

                        adapter = new DangGiao_adapter(list,getContext());
                        rcv.addItemDecoration(new MyDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL, 36));
                        rcv.setItemAnimator(new DefaultItemAnimator());
                        rcv.setLayoutManager(llm);
                        rcv.setAdapter(adapter);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                    Toast.makeText(getContext(),"Error: " + e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error: " + error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("idUser", String.valueOf(user.getIdUser()));
                data.put("trangthai", "0");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}
