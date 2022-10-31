package com.example.test;

import static com.example.test.LoginActivity.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import pk_cart.CartAdapter;

public class CartActivity extends AppCompatActivity {
    ListView listViewCart;
    public static TextView Tongtien;
    Button Muahang;
    public static CartAdapter cartAdapter;
    ImageButton back;
    ImageButton btnplus, btnminus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Anhxa();
        EventUntil();
        Back();
        SetupQty();

        Muahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getFullname() != null){
                    Intent intent = new Intent(CartActivity.this,CheckoutActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(CartActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void SetupQty() {
        //btnplus.setOnClickListener();
    }

    private void Back() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    public static void EventUntil() {
        long tongtien = 0;
        for(int i = 0 ; i < MainActivity.cartArrayList.size(); i++){
            tongtien += MainActivity.cartArrayList.get(i).getGiaSP();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            Tongtien.setText(decimalFormat.format(tongtien) + " VNĐ");
        }
    }


    private void Anhxa() {
        listViewCart = findViewById(R.id.listviewcart);
        Tongtien = findViewById(R.id.txtTongTien);
        Muahang = findViewById(R.id.btnBuy);
        cartAdapter = new CartAdapter(CartActivity.this,MainActivity.cartArrayList);
        listViewCart.setAdapter(cartAdapter);
        back = findViewById(R.id.back);
        btnminus = findViewById(R.id.btnminus);
        btnplus = findViewById(R.id.btnplus);
    }


}