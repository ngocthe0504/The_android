package com.example.test;

import static com.example.test.LoginActivity.idUser;
import static com.example.test.LoginActivity.user;
import static com.example.test.MainActivity.islogin;
import static com.example.test.MainActivity.pendingSMSCount;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InfomationCustomer extends AppCompatActivity {
    Toolbar toolbar;
    TextView smsCountTxt,txtName,txtPhone,txtDob,txtAddress,txtwelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_customer);

        toolbar = findViewById(R.id.toolbarinfo);
        setSupportActionBar(toolbar);
        anhxa();


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
    private  void anhxa(){
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtDob = findViewById(R.id.txtDob);
        txtAddress = findViewById(R.id.txtAddress);
        txtwelcome = findViewById(R.id.txtwelcome);

        txtName.setFocusable(false);
        txtPhone.setFocusable(false);
        txtDob.setFocusable(false);
        txtAddress.setFocusable(false);

        txtName.setText("Họ và tên: " + user.getFullname());
        txtPhone.setText("Số điện thoại: " + user.getPhone());
        txtDob.setText("Ngày sinh: " + user.getDob());
        txtAddress.setText("Địa chỉ: " + user.getAddress());
        txtwelcome.setText("Xin chào, " + user.getFullname());
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case  R.id.actioncart:
                if(islogin!=false){
                    Intent intent = new Intent(InfomationCustomer.this, CartActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(InfomationCustomer.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
        return true;
    }
}