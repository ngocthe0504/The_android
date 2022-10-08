package com.example.ktgk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Student> studentList;
    List<String> listName;

    public StudentAdapter(Context context, int layout, List<String> listName) {
        this.context = context;
        this.layout = layout;
        this.listName = listName;
    }

    public StudentAdapter(Context context, int layout, ArrayList<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvMsv = (TextView) convertView.findViewById(R.id.tvMsv);
        TextView tvCountry = (TextView) convertView.findViewById(R.id.tvCountry);
        TextView tvPhoneNumber = (TextView) convertView.findViewById(R.id.tvPhoneNumber);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        ImageView imgStudent = (ImageView) convertView.findViewById(R.id.imgStudent);
        tvName.setText(studentList.get(i).name);
        tvMsv.setText(studentList.get(i).msv);
        tvCountry.setText(studentList.get(i).country);
        tvPhoneNumber.setText(studentList.get(i).phoneNumber);
        tvEmail.setText(studentList.get(i).email);
        imgStudent.setImageResource(studentList.get(i).img);
        return convertView;

    }
}
