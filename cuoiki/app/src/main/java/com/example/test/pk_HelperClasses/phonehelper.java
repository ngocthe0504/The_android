package com.example.test.pk_HelperClasses;

import java.io.Serializable;

public class phonehelper implements Serializable {
    int idDM;
    int idSP;
    int giaInt;
    String image;
    String title;
    String price;
    String rate;
    String note;
    String sizemanhinh,loaimanhinh,ram,rom,pin;
    int star;
    int star2;
    int star3;
    int star4;

    public phonehelper() {
    }

    public phonehelper(int idDM, int idSP, int giaInt, String image, String title, String price, String rate, String note, String sizemanhinh, String loaimanhinh, String ram, String rom, String pin, int star, int star2, int star3, int star4) {
        this.idDM = idDM;
        this.idSP = idSP;
        this.giaInt = giaInt;
        this.image = image;
        this.title = title;
        this.price = price;
        this.rate = rate;
        this.note = note;
        this.sizemanhinh = sizemanhinh;
        this.loaimanhinh = loaimanhinh;
        this.ram = ram;
        this.rom = rom;
        this.pin = pin;
        this.star = star;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
    }

    public int getIdDM() {
        return idDM;
    }

    public void setIdDM(int idDM) {
        this.idDM = idDM;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getGiaInt() {
        return giaInt;
    }

    public void setGiaInt(int giaInt) {
        this.giaInt = giaInt;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSizemanhinh() {
        return sizemanhinh;
    }

    public void setSizemanhinh(String sizemanhinh) {
        this.sizemanhinh = sizemanhinh;
    }

    public String getLoaimanhinh() {
        return loaimanhinh;
    }

    public void setLoaimanhinh(String loaimanhinh) {
        this.loaimanhinh = loaimanhinh;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getStar2() {
        return star2;
    }

    public void setStar2(int star2) {
        this.star2 = star2;
    }

    public int getStar3() {
        return star3;
    }

    public void setStar3(int star3) {
        this.star3 = star3;
    }

    public int getStar4() {
        return star4;
    }

    public void setStar4(int star4) {
        this.star4 = star4;
    }
}

