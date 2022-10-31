package com.example.test.fragment_ordered;

import static com.example.test.LoginActivity.user;
import static com.example.test.MainActivity.http;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import pk_lichsuhonhang.DaGiao;
import pk_lichsuhonhang.MyDividerItemDecoration;
import pk_lichsuhonhang.danggiao;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.R;
import com.example.test.adapter_ordered.Dagiao_adapter;
import com.example.test.adapter_ordered.DangGiao_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaGiaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaGiaoFragment extends Fragment {
    List<DaGiao> list = new ArrayList<>();
    RecyclerView rcv;
    Dagiao_adapter adapter;
    private String URL = http+"androidwebservice/LichSuDH.php";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DaGiaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaGiaoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DaGiaoFragment newInstance(String param1, String param2) {
        DaGiaoFragment fragment = new DaGiaoFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dagiao, container, false);


        rcv = view.findViewById(R.id.chiTiet_dagiao);
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

                            DaGiao dagiao = new DaGiao();
                            dagiao.setTitle(object.getString("Tittle"));
                            dagiao.setImage(http+object.getString("HinhAnh"));
                            dagiao.setSoluong(object.getString("SoLuong"));
                            int sl = Integer.parseInt(object.getString("SoLuong"));
                            dagiao.setTongtien(""+ (sl *  Integer.parseInt(object.getString("DonGia"))) );

                            list.add(dagiao);

                        }


                        LinearLayoutManager llm = new LinearLayoutManager(getContext());
                        llm.setOrientation(LinearLayoutManager.VERTICAL);

                        adapter = new Dagiao_adapter(list,getContext());
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
                data.put("trangthai", "1");
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}