package com.example.btkt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.btkt.model.Singer;

import java.util.ArrayList;

public class ListSingerActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Singer> arrayList;
    SingerAdapter singerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_singer);
        lv = (ListView) findViewById(R.id.lvSinger);
        arrayList = new ArrayList<>();
        arrayList.add(new Singer("Nguyễn Đức Cường", R.drawable.logo,"Đen Vâu", 5, "Việt Nam" ));
        arrayList.add(new Singer("Nguyễn Thanh Tùng", R.drawable.st,"Sơn Tùng", 5, "Việt Nam" ));
        arrayList.add(new Singer("Rose", R.drawable.rose,"Rosé", 5, "Korea" ));
        arrayList.add(new Singer("Jutin bieber", R.drawable.jt,"Jutin bieber", 5, "Anh" ));
        arrayList.add(new Singer("Duy Mạnh", R.drawable.dm,"Duy Mạnh", 5, "Việt Nam" ));
        singerAdapter = new SingerAdapter(this,R.layout.singer,arrayList);
        lv.setAdapter(singerAdapter);
     }

}