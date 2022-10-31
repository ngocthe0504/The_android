package com.example.test;

public class User {
    int idUser;
    String Username;
    String Pass;
    String phone;
    String dob;
    String address;
    String fullname;

    public User(int idUser, String username, String pass, String phone, String dob, String address, String fullname) {
        this.idUser = idUser;
        Username = username;
        Pass = pass;
        this.phone = phone;
        this.dob = dob;
        this.address = address;
        this.fullname = fullname;
    }

    public User() {

    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
