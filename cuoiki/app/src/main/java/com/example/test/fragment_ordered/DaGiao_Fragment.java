package com.example.test.fragment_ordered;


import com.example.test.adapter_ordered.Dagiao_adapter;

import java.util.ArrayList;

public class DaGiao_Fragment{

    ArrayList<Dagiao_adapter> DagiaoLaocations;
    Dagiao_adapter adapter;

    public DaGiao_Fragment(ArrayList<Dagiao_adapter> DagiaoLaocations, ListItemClickListener listener) {
        this.DagiaoLaocations = DagiaoLaocations;
//        mOnClickListener = listener;
    }
}
