package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button btnCreateDatabase=null;
        Button btnInsertCategory=null;
        Button btnShowCategoryList=null;
        Button btnShowCategoryList2=null;
        Button btnTransaction=null;
        Button btnShowDetail=null;
        Button btnInsertComputer=null;
        final int OPEN_AUTHOR_DIALOG=1;
        final int SEND_DATA_FROM_AUTHOR_ACTIVITY=2;
        SQLiteDatabase database=null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsertCategory=(Button) findViewById(R.id.btnAdd);
        btnInsertCategory.setOnClickListener(new MyEvent());
        btnShowCategoryList=(Button) findViewById(R.id.btnView);
        btnShowCategoryList.setOnClickListener(new MyEvent());
        btnInsertComputer=(Button) findViewById(R.id.btnAdd);
        btnInsertComputer.setOnClickListener(new MyEvent());
        getDatabase();
    }

}