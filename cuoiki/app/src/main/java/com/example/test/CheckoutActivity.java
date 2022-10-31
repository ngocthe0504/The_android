package com.example.test;

import static com.example.test.LoginActivity.user;
import static com.example.test.MainActivity.cartArrayList;
import static com.example.test.MainActivity.http;
import static com.example.test.MainActivity.islogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pk_item_checkout.Checkout_Apdater;
import pk_item_checkout.Item_checkout;

public class CheckoutActivity extends AppCompatActivity {
    ListView lvCheckout;
    Checkout_Apdater adapter;
    TextView txtTongTien;
    ArrayList<Item_checkout> listCheckout = new ArrayList<>();
    LinearLayout layoutAddress;
    TextView txtName,txtPhone,txtAddress;
    Button btnCheckout;
    int tong = 0;
    private static  final String URL_Checkout = http+"androidwebservice/checkout.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        anhxa();

        getdata();
        changeAddress();
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });
    }

    private void DialogSubmit(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Thông báo");
        //alert.setIcon(R.);
        alert.setMessage("Đặt hàng thành công!");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                cartArrayList.clear();
            }
        });

        alert.show();
    }

    private void anhxa() {
        txtTongTien = findViewById(R.id.txtMoneyCheckout);
        layoutAddress = findViewById(R.id.layout_info_checkout);
        txtName = findViewById(R.id.name_checkout);
        txtPhone = findViewById(R.id.phone_checkout);
        txtAddress = findViewById(R.id.address_checkout);
         btnCheckout = findViewById(R.id.btnCheckout);

        //get address
        txtName.setText(user.getFullname());
        txtPhone.setText(user.getPhone());
        txtAddress.setText(user.getAddress());
    }
    private void changeAddress() {
        layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.CENTER);
            }
        });
    }
    private void openDialog(int gravity){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_address);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        }
        else{
            dialog.setCancelable(false);
        }

        Button btnAdd = dialog.findViewById(R.id.btnSave_address);
        Button btnCancel = dialog.findViewById(R.id.btnCancel_address);
        EditText EditName = dialog.findViewById(R.id.edit_name_checkout);
        EditText EditPhone = dialog.findViewById(R.id.edit_sdt_checkout);
        EditText EditAddress = dialog.findViewById(R.id.edit_address_checkout);

        EditName.setText(txtName.getText());
        EditPhone.setText(txtPhone.getText());
        EditAddress.setText(txtAddress.getText());


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtName.setText(EditName.getText());
                txtPhone.setText(EditPhone.getText());
                txtAddress.setText(EditAddress.getText());
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void getdata() {
        lvCheckout = findViewById(R.id.listCheckout);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    for(int i = 0 ; i < cartArrayList.size() ; i ++) {
        int gia = (int) cartArrayList.get(i).getGiaSP();

        Item_checkout item_checkout = new Item_checkout();
        item_checkout.setTittle(""+cartArrayList.get(i).getTenSP());
        item_checkout.setGia(gia/cartArrayList.get(i).getSoLuong());
        item_checkout.setSoluong(cartArrayList.get(i).getSoLuong());
        item_checkout.setImage(cartArrayList.get(i).getHinhAnh());
        item_checkout.setId(cartArrayList.get(i).getIdSP());
        tong += cartArrayList.get(i).getGiaSP();
    listCheckout.add(item_checkout);
    }
    txtTongTien.setText(decimalFormat.format(tong )  + " VNĐ");
        adapter = new Checkout_Apdater(CheckoutActivity.this,listCheckout,R.layout.item_checkout);
        lvCheckout.setAdapter(adapter);
    }

    public void checkout() {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Checkout, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonObject  = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        if(success.equals("1")){
                            //Toast.makeText(CheckoutActivity.this,"OK: " ,Toast.LENGTH_SHORT).show();
                            DialogSubmit();

                        }

                    }catch(JSONException e){
                        e.printStackTrace();
                        Toast.makeText(CheckoutActivity.this,"Error: " + e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    JSONArray jsonArray = new JSONArray();
                    for(int i = 0 ; i < cartArrayList.size();i++){
                        JSONObject jsonObject = new JSONObject();
                        try{
                            jsonObject.put("idSP",cartArrayList.get(i).getIdSP());
                            jsonObject.put("soluong",cartArrayList.get(i).getSoLuong());
                            jsonObject.put("giatien",cartArrayList.get(i).getGiaSP()/cartArrayList.get(i).getSoLuong());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(jsonObject);
                    }
                    Map<String, String> data = new HashMap<>();
                    String[] arrOfStr = cartArrayList.get(0).getHinhAnh().split(http);
                    data.put("idUser", String.valueOf(user.getIdUser()));
                    data.put("TongTien", String.valueOf(tong));
                    data.put("tennguoinhan", txtName.getText().toString());
                    data.put("sdt", txtPhone.getText().toString());
                    data.put("diachi", txtAddress.getText().toString());
                    data.put("hinhanh", arrOfStr[1]);
                    data.put("chitiet", jsonArray.toString());

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

    }
}