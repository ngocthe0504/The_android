package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfirmPassword;
    Button btnRegister;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String confirmPassword = edtConfirmPassword.getText().toString();

                if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || !password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Nhập lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("userName", userName);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("confirmPassword", confirmPassword);
                setResult(LoginActivity.REQUEST_CODE_REGISTER, intent);
                finish();
            }
        });
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}