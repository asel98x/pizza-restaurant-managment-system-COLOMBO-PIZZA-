package com.example.colombopizza;

public class DB_apprtizer {
    private int F_id ;
    private String F_name;
    private String F_description;
    private double F_price;
    byte[] img;

    public int getF_id() {
        return F_id;
    }

    public void setF_id(int f_id) {
        F_id = f_id;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public String getF_description() {
        return F_description;
    }

    public void setF_description(String f_description) {
        F_description = f_description;
    }

    public double getF_price() {
        return F_price;
    }

    public void setF_price(double f_price) {
        F_price = f_price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
