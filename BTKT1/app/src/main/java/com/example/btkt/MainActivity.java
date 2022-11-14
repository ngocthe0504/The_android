package com.example.btkt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnDangKi;
    private Button btnDangNhap;
    private EditText edtUsername;
    private EditText edtPassword;
    private String user = "admin";
    private String pass = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnDangNhap = (Button) findViewById(R.id.dangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ndUser = edtUsername.getText().toString();
                String ndPass = edtPassword.getText().toString();
                if (ndUser.length() == 0|| ndPass.length() == 0) {
                    Toast.makeText(MainActivity.this,"Tên đăng nhập hoặc mật khẩu không được để trống!", Toast.LENGTH_LONG).show();
                } else if(ndUser.equals(user) && ndPass.equals(pass)) {
                    Intent intent = new Intent(MainActivity.this, ListSingerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this,"Tên đăng nhập hoặc mật khẩu sai!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}