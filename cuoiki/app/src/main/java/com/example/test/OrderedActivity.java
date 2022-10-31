package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import pk_lichsuhonhang.MyViewPagerAdapter;

public class OrderedActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    BottomNavigationView bottomNavigationView;
    RecyclerView phoneRecycler;
    ImageButton btnBack;
    RecyclerView.Adapter adapter;
    int images[] = {R.drawable.ip13_pink, R.drawable.iphon13};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);


        myViewPagerAdapter = new MyViewPagerAdapter(this    );
        viewPager.setAdapter(myViewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Đang giao");
                    break;
                case 1:
                    tab.setText("Đã giao");
                    break;
                case 2:
                    tab.setText("Đã hủy");
                    break;
            }
        }).attach();

        btnBack = findViewById(R.id.back_Ordered);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}