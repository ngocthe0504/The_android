package com.example.foodapp.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String title;
    private String content;
    float price;

    public Food(int id, String title, String content, float price) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
    }

    public Food(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
