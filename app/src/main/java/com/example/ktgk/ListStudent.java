package com.example.ktgk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListStudent extends AppCompatActivity {
    ListView lvStudent;
    ArrayList<String> listName;
    ArrayAdapter<String> adapter;

//    ArrayList<Student> listData;
//    ArrayList<String> listName;
    Context context;
//    StudentAdapter studentAdapter;
//    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        lvStudent = (ListView) findViewById(R.id.lvStudent);
        context = this;
        listName = new ArrayList<>();
        listName.add("Mai Xuân Duy");
        listName.add("Phạm Quốc Đôn");
        listName.add("Trần Hoàng Vũ");
        listName.add("Nguyễn Thành Đạt");
        listName.add("Trương Công Tạn");
        listName.add("Lưu Văn Đức");
        listName.add("Trương Hồng Sơn");
        listName.add("Trương Văn Tuấn");
        listName.add("Nguyễn Thị Kim Phượng");
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, listName);
        lvStudent.setAdapter(adapter);
        ////        listData = new ArrayList<>();
////        listData.add(new Student("Mai Xuân Duy", "180212312312", R.drawable.user, "Quảng Nam", "0898888674", "duymai@gmail.com"));
////        listData.add(new Student("Phạm Quốc Đôn", "180212312312", R.drawable.user, "Huế", "02346647854", "donpham@gmail.com"));
////        listData.add(new Student("Trần Hoàng Vũ", "180124242412", R.drawable.user, "Quảng Nam", "01346212452", "vutran@gmail.com"));
////        listData.add(new Student("Nguyễn Thành Đạt", "180214122412", R.drawable.user, "Đà Lạt", "01342155244", "datnguyen@gmail.com"));
////        listData.add(new Student("Trương Công Tạn", "131241241243", R.drawable.user, "Quảng Trị", "01354146451", "tantruong@gmail.com"));
////        listData.add(new Student("Lưu Văn Đức", "131241241243", R.drawable.user, "Quảng Trị", "01354146451", "ducluu@gmail.com"));
////        listData.add(new Student("Nguyễn Thị Kim Phượng", "131241241243", R.drawable.user, "Quảng Nam", "012341677413", "kimphuong@gmail.com"));
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lvStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ListStudent.this, StudentDetail.class);
                        startActivity(intent);
                    }
                });
            }
        });
   }
}