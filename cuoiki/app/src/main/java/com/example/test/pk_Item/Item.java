package com.example.test.pk_Item;

import java.io.Serializable;

public class Item implements Serializable {
    String  image;
    String title;
    int id;
    public Item() {

    }
    public Item(String image, String title, int id) {
        this.image = image;
        this.title = title;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
