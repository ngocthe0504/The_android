package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.example.foodapp.adapter.FoodAdapter;
import com.example.foodapp.model.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListFoodActivity extends AppCompatActivity {
    ListView listView;
    List<Food> list = new ArrayList<>();
    FoodAdapter foodAdapter;
    int currentIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);
        listView = findViewById(R.id.lvFood);
//        list.add(new Food(R.drawable.food1, "Bánh tráng kẹp", "ngon", 15000));
//        list.add(new Food(R.drawable.food3, "Ốc hút", "ngon", 15000));
//        list.add(new Food(R.drawable.food4, "Bánh tráng trộn", "ngon", 15000));
//        list.add(new Food(R.drawable.food5, "nem lụi", "ngon", 15000));
        read();

        foodAdapter = new FoodAdapter(this, list);
        listView.setAdapter(foodAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_content, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.menu_edit_item:
                this.currentIndex = index;
                showDialog();
                return true;
            case R.id.menu_delete_item:
                list.remove(index);
                foodAdapter.notifyDataSetChanged();
                return super.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_add_new_item:

                return true;
            case R.id.menu_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void readFiles() {
        ObjectInputStream ois = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(getFileStreamPath("food.dat"));
            ois = new ObjectInputStream(fis);
            list = (List<Food>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void saveFiles() {
        ObjectOutputStream oss = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(getFileStreamPath("food.dat"));
            oss = new ObjectOutputStream(fos);
            oss.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oss != null) {
                try {
                    oss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void save() {
        String json = new Gson().toJson(list);
        SharedPreferences sharedPreferences = getSharedPreferences("food", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("list", json);
        editor.commit();
    }
    private void read() {
        SharedPreferences sharedPreferences = getSharedPreferences("food", MODE_PRIVATE);
        String json = sharedPreferences.getString("list", "[]");
        Gson gson = new Gson();
        list = gson.fromJson(json, new TypeToken<List<Food>>(){}.getType());
        if (list == null) {
            list = new ArrayList<>();
        }
    }
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_food, null);
        @SuppressLint("WrongViewCast") EditText edtTitle  = findViewById(R.id.tvTitle);
        @SuppressLint("WrongViewCast") EditText edtContent  = findViewById(R.id.tvContent);
        @SuppressLint("WrongViewCast") EditText edtPrice  = findViewById(R.id.tvPrice);
        if(currentIndex>=0) {
            edtTitle.setText(list.get(currentIndex).getTitle());
            edtContent.setText(list.get(currentIndex).getContent());
            edtPrice.setText(String.valueOf(list.get(currentIndex).getContent()));
        }
        builder.setView(view);

        builder.setTitle("Thêm").setPositiveButton("lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                float price = Float.parseFloat(edtPrice.getText().toString());
                if (currentIndex>=0) {
                    list.get(currentIndex).setTitle(title);
                    list.get(currentIndex).setContent(content);
                    list.get(currentIndex).setPrice(price);
                    currentIndex = -1;
                } else {
                    Food food = new Food(R.drawable.food1, title, content, price);
                    list.add(food);
                }
                save();
                foodAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton("Huỷ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}