package com.sjp.service_1.beans;

public class CatelogItem {
    private String name;
    private  String des;
    private  int rating;

    public CatelogItem(String name, String des, int rating) {
        this.name = name;
        this.des = des;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public int getRating() {
        return rating;
    }
}
