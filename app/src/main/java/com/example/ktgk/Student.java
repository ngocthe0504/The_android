package com.example.ktgk;

public class Student {
    public String name;
    public String msv;
    public int img;
    public String country;
    public String phoneNumber;
    public String email;


    public Student(String name, String msv, int img, String country, String phoneNumber, String email) {
        this.name = name;
        this.msv = msv;
        this.img = img;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
